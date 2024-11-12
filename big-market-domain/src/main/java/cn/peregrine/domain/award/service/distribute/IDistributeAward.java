package cn.peregrine.domain.award.service.distribute;

import cn.peregrine.domain.award.model.entity.DistributeAwardEntity;

/**
 * @description 分发奖品接口
 */
public interface IDistributeAward {

    void giveOutPrizes(DistributeAwardEntity distributeAwardEntity);

}

