package cn.peregrine.domain.activity.service.quota;

import cn.peregrine.domain.activity.model.entity.ActivityCountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.ActivitySkuEntity;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import cn.peregrine.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service
 * @className: RaffleActivitySupport
 * @author: Peregrine Calder
 * @description: 抽奖活动的支撑类
 * @date: 2024/4/26 23:22
 * @version: 1.0
 */
public class RaffleActivityAccountQuotaSupport {
    protected DefaultActivityChainFactory defaultActivityChainFactory;
    protected IActivityRepository activityRepository;
    public RaffleActivityAccountQuotaSupport(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        this.activityRepository = activityRepository;
        this.defaultActivityChainFactory = defaultActivityChainFactory;
    }
    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySku(sku);
    }

    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }



}
