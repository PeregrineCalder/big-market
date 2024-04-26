package cn.peregrine.domain.activity.service.rule.impl;

import cn.peregrine.domain.activity.model.entity.ActivityCountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.ActivitySkuEntity;
import cn.peregrine.domain.activity.service.rule.AbstractActionChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service.rule.impl
 * @className: ActivityBaseActionChain
 * @author: Peregrine Calder
 * @description: TODO
 * @date: 2024/4/26 23:15
 * @version: 1.0
 */
@Slf4j
@Component("activity_base_action")
public class ActivityBaseActionChain extends AbstractActionChain {
    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-基础信息【有效期、状态】校验开始");
        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }
}
