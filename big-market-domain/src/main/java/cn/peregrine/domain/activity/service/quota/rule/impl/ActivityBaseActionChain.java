package cn.peregrine.domain.activity.service.quota.rule.impl;

import cn.peregrine.domain.activity.model.entity.ActivityCountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.ActivitySkuEntity;
import cn.peregrine.domain.activity.model.valobj.ActivityStateVO;
import cn.peregrine.domain.activity.service.quota.rule.AbstractActionChain;
import cn.peregrine.types.enums.ResponseCode;
import cn.peregrine.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        log.info("活动责任链-基础信息【有效期、状态、库存(sku)】校验开始。sku:{} activityId:{}", activitySkuEntity.getSku(), activityEntity.getActivityId());
        // 校验；活动状态
        if (!ActivityStateVO.open.equals(activityEntity.getState())) {
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(), ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        // 校验；活动日期「开始时间 <- 当前时间 -> 结束时间」
        Date currentDate = new Date();
        if (activityEntity.getBeginDateTime().after(currentDate) || activityEntity.getEndDateTime().before(currentDate)) {
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(), ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }
        // 校验；活动sku库存 「剩余库存从缓存获取的」
        if (activitySkuEntity.getStockCountSurplus() <= 0) {
            throw new AppException(ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getCode(), ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getInfo());
        }
        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }
}
