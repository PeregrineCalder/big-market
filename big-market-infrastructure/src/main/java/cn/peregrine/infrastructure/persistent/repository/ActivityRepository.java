package cn.peregrine.infrastructure.persistent.repository;

import cn.peregrine.domain.activity.model.entity.ActivityCountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.ActivitySkuEntity;
import cn.peregrine.domain.activity.model.valobj.ActivityStateVO;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import cn.peregrine.infrastructure.persistent.dao.IRaffleActivityCountDao;
import cn.peregrine.infrastructure.persistent.dao.IRaffleActivityDao;
import cn.peregrine.infrastructure.persistent.dao.IRaffleActivitySkuDao;
import cn.peregrine.infrastructure.persistent.po.RaffleActivity;
import cn.peregrine.infrastructure.persistent.po.RaffleActivityCount;
import cn.peregrine.infrastructure.persistent.po.RaffleActivitySku;
import cn.peregrine.infrastructure.persistent.redis.IRedisService;
import cn.peregrine.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.infrastructure.persistent.repository
 * @className: ActivityRepository
 * @author: Peregrine Calder
 * @description: 活动仓储服务
 * @date: 2024/4/25 23:39
 * @version: 1.0
 */
@Repository
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IRedisService redisService;
    @Resource
    private IRaffleActivityDao raffleActivityDao;
    @Resource
    private IRaffleActivitySkuDao raffleActivitySkuDao;
    @Resource
    private IRaffleActivityCountDao raffleActivityCountDao;

    @Override
    public ActivitySkuEntity queryActivitySku(Long sku) {
        RaffleActivitySku raffleActivitySku = raffleActivitySkuDao.queryActivitySku(sku);
        return ActivitySkuEntity.builder()
                .sku(raffleActivitySku.getSku())
                .activityId(raffleActivitySku.getActivityId())
                .activityCountId(raffleActivitySku.getActivityCountId())
                .stockCount(raffleActivitySku.getStockCount())
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .build();
    }

    @Override
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redisService.getValue(cacheKey);
        if (activityEntity != null) {
            return activityEntity;
        }
        // 从库中获取数据
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .activityDesc(raffleActivity.getActivityDesc())
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityStateVO.valueOf(raffleActivity.getState()))
                .build();
        redisService.setValue(cacheKey, activityEntity);
        return activityEntity;
    }

    @Override
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redisService.getValue(cacheKey);
        if (activityCountEntity != null) {
            return activityCountEntity;
        }
        // 从库中获取数据
        RaffleActivityCount raffleActivityCount = raffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);
        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(raffleActivityCount.getActivityCountId())
                .totalCount(raffleActivityCount.getTotalCount())
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .build();
        redisService.setValue(cacheKey, activityCountEntity);
        return activityCountEntity;
    }
}
