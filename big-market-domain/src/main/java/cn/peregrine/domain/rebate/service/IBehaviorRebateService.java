package cn.peregrine.domain.rebate.service;

import cn.peregrine.domain.rebate.model.entity.BehaviorEntity;
import cn.peregrine.domain.rebate.model.entity.BehaviorRebateOrderEntity;

import java.util.List;

/**
 * @description 行为返利服务接口
 */

public interface IBehaviorRebateService {
    /**
     * 创建行为动作的入账订单
     *
     * @param behaviorEntity 行为实体对象
     * @return 订单ID
     */
    List<String> createOrder(BehaviorEntity behaviorEntity);

    List<BehaviorRebateOrderEntity> queryOrderByOutBusinessNo(String userId, String outBusinessNo);
}
