package cn.peregrine.domain.activity.repository;

import cn.peregrine.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import cn.peregrine.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.peregrine.domain.activity.model.entity.*;
import cn.peregrine.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;
import java.util.List;

/**
 * @description 活动仓储接口
 */
public interface IActivityRepository {
    ActivitySkuEntity queryActivitySku(Long sku);
    ActivityEntity queryRaffleActivityByActivityId(Long activityId);
    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);
    void doSaveOrder(CreateQuotaOrderAggregate createOrderAggregate);
    void cacheActivitySkuStockCount(String cacheKey, Integer stockCount);
    boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);
    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO activitySkuStockKeyVO);
    ActivitySkuStockKeyVO takeQueueValue();
    void clearQueueValue();
    void updateActivitySkuStock(Long sku);
    void clearActivitySkuStock(Long sku);
    UserRaffleOrderEntity queryNoUsedRaffleOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);
    ActivityAccountEntity queryActivityAccountByUserId(String userId, Long activityId);
    ActivityAccountMonthEntity queryActivityAccountMonthByUserId(String userId, Long activityId, String month);
    ActivityAccountDayEntity queryActivityAccountDayByUserId(String userId, Long activityId, String day);
    void saveCreatePartakeOrderAggregate(CreatePartakeOrderAggregate createPartakeOrderAggregate);
    List<ActivitySkuEntity> queryActivitySkuListByActivityId(Long activityId);
    Integer queryRaffleActivityAccountDayPartakeCount(Long activityId, String userId);

}
