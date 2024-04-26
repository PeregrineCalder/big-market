package cn.peregrine.infrastructure.persistent.dao;

import cn.peregrine.infrastructure.persistent.po.RaffleActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 抽奖活动次数配置表 Dao
 */
@Mapper
public interface IRaffleActivityCountDao {
    RaffleActivityCount queryRaffleActivityCountByActivityCountId(Long activityCountId);


}
