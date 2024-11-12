package cn.peregrine.domain.award.service;

import cn.peregrine.domain.award.model.entity.DistributeAwardEntity;
import cn.peregrine.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @description 奖品服务接口
 */
public interface IAwardService {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);
    /**
     * 配送发货奖品
     */
    void distributeAward(DistributeAwardEntity distributeAwardEntity);

}