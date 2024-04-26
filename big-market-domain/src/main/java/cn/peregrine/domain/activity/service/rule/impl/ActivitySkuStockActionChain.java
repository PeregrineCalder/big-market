package cn.peregrine.domain.activity.service.rule.impl;

import cn.peregrine.domain.activity.model.entity.ActivityCountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.ActivitySkuEntity;
import cn.peregrine.domain.activity.service.rule.AbstractActionChain;
import cn.peregrine.domain.activity.service.rule.IActionChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service.rule.impl
 * @className: ActivitySkuStockActionChain
 * @author: Peregrine Calder
 * @description: TODO
 * @date: 2024/4/26 23:17
 * @version: 1.0
 */
@Slf4j
@Component("activity_sku_stock_action")
public class ActivitySkuStockActionChain extends AbstractActionChain {
    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-商品库存处理【校验&扣减】开始");
        return true;
    }
}
