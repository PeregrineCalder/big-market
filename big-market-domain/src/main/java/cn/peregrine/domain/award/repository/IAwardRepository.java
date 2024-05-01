package cn.peregrine.domain.award.repository;

import cn.peregrine.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @description 奖品仓储服务
 */

public interface IAwardRepository {
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);
}
