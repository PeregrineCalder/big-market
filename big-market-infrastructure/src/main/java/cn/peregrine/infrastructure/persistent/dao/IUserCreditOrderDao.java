package cn.peregrine.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.peregrine.infrastructure.persistent.po.UserCreditOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 用户积分流水单 DAO
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserCreditOrderDao {

    void insert(UserCreditOrder userCreditOrderReq);

}

