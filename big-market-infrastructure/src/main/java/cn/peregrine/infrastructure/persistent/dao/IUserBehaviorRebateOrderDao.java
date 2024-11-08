package cn.peregrine.infrastructure.persistent.dao;
import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.peregrine.infrastructure.persistent.po.UserBehaviorRebateOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 用户行为返利流水订单表
 */
@Mapper
@DBRouterStrategy(splitTable = true)

public interface IUserBehaviorRebateOrderDao {
    void insert(UserBehaviorRebateOrder userBehaviorRebateOrder);
    @DBRouter
    List<UserBehaviorRebateOrder> queryOrderByOutBusinessNo(UserBehaviorRebateOrder userBehaviorRebateOrderReq);
}
