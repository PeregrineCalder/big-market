package cn.peregrine.domain.activity.repository;

import cn.peregrine.domain.activity.model.aggregate.CreateOrderAggregate;
import cn.peregrine.domain.activity.model.entity.ActivityCountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.ActivitySkuEntity;
import cn.peregrine.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;

/**
 * @description 活动仓储接口
 */
public interface IActivityRepository {
    ActivitySkuEntity queryActivitySku(Long sku);
    ActivityEntity queryRaffleActivityByActivityId(Long activityId);
    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);
    void doSaveOrder(CreateOrderAggregate createOrderAggregate);
    void cacheActivitySkuStockCount(String cacheKey, Integer stockCount);
    boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);
    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO activitySkuStockKeyVO);
    ActivitySkuStockKeyVO takeQueueValue();
    void clearQueueValue();
    void updateActivitySkuStock(Long sku);
    void clearActivitySkuStock(Long sku);
}
