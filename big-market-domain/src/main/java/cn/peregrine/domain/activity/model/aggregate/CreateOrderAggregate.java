package cn.peregrine.domain.activity.model.aggregate;

import cn.peregrine.domain.activity.model.entity.ActivityAccountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.model.aggregate
 * @className: CreateOrderAggregate
 * @author: Peregrine Calder
 * @description: 下单聚合对象
 * @date: 2024/4/25 23:14
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderAggregate {
    /**
     * 活动账户实体
     */
    private ActivityAccountEntity activityAccountEntity;
    /**
     * 活动订单实体
     */
    private ActivityOrderEntity activityOrderEntity;

}
