package cn.peregrine.domain.activity.service;

import cn.peregrine.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.peregrine.domain.activity.model.entity.*;
import cn.peregrine.domain.activity.model.valobj.OrderStateVO;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import cn.peregrine.domain.activity.service.rule.IActionChain;
import cn.peregrine.domain.activity.service.rule.factory.DefaultActivityChainFactory;
import cn.peregrine.types.enums.ResponseCode;
import cn.peregrine.types.exception.AppException;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service
 * @className: AbstractRaffleActivity
 * @author: Peregrine Calder
 * @description: 抽奖活动抽象类，定义标准的流程
 * @date: 2024/4/25 23:26
 * @version: 1.0
 */
@Slf4j
public abstract class AbstractRaffleActivity extends RaffleActivitySupport implements IRaffleOrder{
    public AbstractRaffleActivity(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        super(activityRepository, defaultActivityChainFactory);
    }

    @Override
    public String createSkuRechargeOrder(SkuRechargeEntity skuRechargeEntity) {
        // 1. 参数校验
        String userId = skuRechargeEntity.getUserId();
        Long sku = skuRechargeEntity.getSku();
        String outBusinessNo = skuRechargeEntity.getOutBusinessNo();
        if (null == sku || StringUtils.isBlank(userId) || StringUtils.isBlank(outBusinessNo)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        // 2. 查询基础信息
        // 2.1 通过sku查询活动信息
        ActivitySkuEntity activitySkuEntity = queryActivitySku(sku);
        // 2.2 查询活动信息
        ActivityEntity activityEntity = queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        // 2.3 查询次数信息（用户在活动上可参与的次数）
        ActivityCountEntity activityCountEntity = queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
        // 3. 活动动作规则校验 todo 后续处理规则过滤流程，暂时也不处理责任链结果
        IActionChain actionChain = defaultActivityChainFactory.openActionChain();
        boolean success = actionChain.action(activitySkuEntity, activityEntity, activityCountEntity);
        // 4. 构建订单聚合对象
        CreateOrderAggregate createOrderAggregate = buildOrderAggregate(skuRechargeEntity, activitySkuEntity, activityEntity, activityCountEntity);
        // 5. 保存订单
        doSaveOrder(createOrderAggregate);
        // 6. 返回单号
        return createOrderAggregate.getActivityOrderEntity().getOrderId();
    }
    protected abstract CreateOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

    protected abstract void doSaveOrder(CreateOrderAggregate createOrderAggregate);

}
