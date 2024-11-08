package cn.peregrine.domain.rebate.model.aggregate;

import cn.peregrine.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.peregrine.domain.rebate.model.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.rebate.model.aggregate
 * @className: BehaviorRebateAggregate
 * @author: Peregrine Calder
 * @description: 行为返利聚合对象
 * @date: 2024/8/6 23:23
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorRebateAggregate {
    /** 用户ID */
    private String userId;
    /** 行为返利订单实体对象 */
    private BehaviorRebateOrderEntity behaviorRebateOrderEntity;
    /** 任务实体对象 */
    private TaskEntity taskEntity;

}
