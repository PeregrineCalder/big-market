package cn.peregrine.domain.activity.service.quota.rule;

import cn.peregrine.domain.activity.model.entity.ActivityCountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.ActivitySkuEntity;
/**
 * @description 下单规则过滤接口
 */
public interface IActionChain extends IActionChainArmory{
    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
