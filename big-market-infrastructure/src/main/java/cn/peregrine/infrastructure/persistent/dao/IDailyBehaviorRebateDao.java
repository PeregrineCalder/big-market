package cn.peregrine.infrastructure.persistent.dao;

import cn.peregrine.infrastructure.persistent.po.DailyBehaviorRebate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 日常行为返利活动配置
 */
@Mapper
public interface IDailyBehaviorRebateDao {
    List<DailyBehaviorRebate> queryDailyBehaviorRebateByBehaviorType(String behaviorType);

}
