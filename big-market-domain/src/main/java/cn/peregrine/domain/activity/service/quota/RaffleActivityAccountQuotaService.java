package cn.peregrine.domain.activity.service.quota;

import cn.peregrine.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.peregrine.domain.activity.model.entity.*;
import cn.peregrine.domain.activity.model.valobj.ActivitySkuStockKeyVO;
import cn.peregrine.domain.activity.model.valobj.OrderStateVO;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import cn.peregrine.domain.activity.service.IRaffleActivitySkuStockService;
import cn.peregrine.domain.activity.service.quota.policy.ITradePolicy;
import cn.peregrine.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service
 * @className: RaffleActivityService
 * @author: Peregrine Calder
 * @description: 抽奖活动服务
 * @date: 2024/4/25 23:35
 * @version: 1.0
 */
@Service
public class RaffleActivityAccountQuotaService extends AbstractRaffleActivityAccountQuota implements IRaffleActivitySkuStockService {

    public RaffleActivityAccountQuotaService(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory, Map<String, ITradePolicy> tradePolicyGroup) {
        super(activityRepository, defaultActivityChainFactory, tradePolicyGroup);
    }

    @Override
    protected CreateQuotaOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        // 订单实体对象
        ActivityOrderEntity activityOrderEntity = new ActivityOrderEntity();
        activityOrderEntity.setUserId(skuRechargeEntity.getUserId());
        activityOrderEntity.setSku(skuRechargeEntity.getSku());
        activityOrderEntity.setActivityId(activityEntity.getActivityId());
        activityOrderEntity.setActivityName(activityEntity.getActivityName());
        activityOrderEntity.setStrategyId(activityEntity.getStrategyId());
        // 公司里一般会有专门的雪花算法UUID服务,我们这里直接生成个12位就可以了
        activityOrderEntity.setOrderId(RandomStringUtils.randomNumeric(12));
        activityOrderEntity.setOrderTime(new Date());
        activityOrderEntity.setTotalCount(activityCountEntity.getTotalCount());
        activityOrderEntity.setDayCount(activityCountEntity.getDayCount());
        activityOrderEntity.setMonthCount(activityCountEntity.getMonthCount());
        activityOrderEntity.setPayAmount(activitySkuEntity.getProductAmount());
        activityOrderEntity.setOutBusinessNo(skuRechargeEntity.getOutBusinessNo());
        return CreateQuotaOrderAggregate.builder()
                .userId(skuRechargeEntity.getUserId())
                .activityId(activitySkuEntity.getActivityId())
                .totalCount(activityCountEntity.getTotalCount())
                .dayCount(activityCountEntity.getDayCount())
                .monthCount(activityCountEntity.getMonthCount())
                .activityOrderEntity(activityOrderEntity)
                .build();
    }


    @Override
    public ActivitySkuStockKeyVO takeQueueValue() throws InterruptedException {
        return activityRepository.takeQueueValue();
    }

    @Override
    public ActivitySkuStockKeyVO takeQueueValue(Long sku) throws InterruptedException {
        return activityRepository.takeQueueValue(sku);
    }

    @Override
    public void clearQueueValue() {
        activityRepository.clearQueueValue();
    }

    @Override
    public void clearQueueValue(Long sku) {
        activityRepository.clearQueueValue(sku);
    }

    @Override
    public void updateActivitySkuStock(Long sku) {
        activityRepository.updateActivitySkuStock(sku);
    }

    @Override
    public void clearActivitySkuStock(Long sku) {
        activityRepository.clearActivitySkuStock(sku);
    }

    @Override
    public List<Long> querySkuList() {
        return activityRepository.querySkuList();
    }

    @Override
    public void updateOrder(DeliveryOrderEntity deliveryOrderEntity) {
        activityRepository.updateOrder(deliveryOrderEntity);
    }


    @Override
    public Integer queryRaffleActivityAccountPartakeCount(Long activityId, String userId) {
        return activityRepository.queryRaffleActivityAccountPartakeCount(activityId, userId);
    }

    @Override
    public Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId) {
        return activityRepository.queryRaffleActivityAccountDayPartakeCount(activityId, userId);
    }

    @Override
    public ActivityAccountEntity queryActivityAccountEntity(Long activityId, String userId) {
        return activityRepository.queryActivityAccountEntity(activityId, userId);
    }
}
