package cn.peregrine.domain.rebate.repository;

import cn.peregrine.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.peregrine.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.peregrine.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.peregrine.domain.rebate.model.valobj.DailyBehaviorRebateVO;

import java.util.List;

/**
 * @description 行为返利服务仓储接口
 */

public interface IBehaviorRebateRepository {
    List<DailyBehaviorRebateVO> queryDailyBehaviorRebateConfig(BehaviorTypeVO behaviorTypeVO);

    void saveUserRebateRecord(String userId, List<BehaviorRebateAggregate> behaviorRebateAggregates);

    List<BehaviorRebateOrderEntity> queryOrderByOutBusinessNo(String userId, String outBusinessNo);
}
