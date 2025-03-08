package cn.peregrine.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.peregrine.infrastructure.dao.po.UserAwardRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 用户中奖记录表
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserAwardRecordDao {
    void insert(UserAwardRecord userAwardRecord);

    int updateAwardRecordCompletedState(UserAwardRecord userAwardRecordReq);
}
