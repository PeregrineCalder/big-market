package cn.peregrine.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.peregrine.infrastructure.persistent.po.UserAwardRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 用户中奖记录表
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserAwardRecordDao {
    void insert(UserAwardRecord userAwardRecord);
}
