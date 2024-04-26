package cn.peregrine.domain.activity.service;

import cn.peregrine.domain.activity.model.entity.*;
import cn.peregrine.domain.activity.model.valobj.OrderStateVO;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

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
public abstract class AbstractRaffleActivity implements IRaffleOrder{
    protected IActivityRepository activityRepository;
    public AbstractRaffleActivity(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    @Override
    public ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity) {
        // 通过sku查询活动信息
        ActivitySkuEntity activitySkuEntity = activityRepository.queryActivitySku(activityShopCartEntity.getSku());
        // 查询活动信息
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        // 查询次数信息（用户在活动上可参与的次数）
        ActivityCountEntity activityCountEntity = activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());
        log.info("查询结果：{} {} {}", JSON.toJSONString(activitySkuEntity), JSON.toJSONString(activityEntity), JSON.toJSONString(activityCountEntity));
        return ActivityOrderEntity.builder()
                .state(OrderStateVO.completed)
                .build();
    }
}
