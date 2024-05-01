package cn.peregrine.domain.award.service;

import cn.peregrine.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @description 奖品服务接口
 */
public interface IAwardService {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);
}
