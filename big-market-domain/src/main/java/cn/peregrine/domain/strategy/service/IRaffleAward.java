package cn.peregrine.domain.strategy.service;

import cn.peregrine.domain.strategy.model.entity.StrategyAwardEntity;
import cn.peregrine.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

import java.util.List;

/**
 * @author: Peregrine Calder
 * @description: 策略奖品接口
 * @version: 1.0
 */
public interface IRaffleAward {
    /**
     * 根据策略ID查询抽奖奖品列表配置
     *
     * @param strategyId 策略ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);

    /**
     * 根据活动ID查询抽奖奖品列表配置
     *
     * @param activityId 活动ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardListByActivityId(Long activityId);

    /**
     * 查询有效活动的奖品配置
     *
     * @return 奖品配置列表
     */
    List<StrategyAwardStockKeyVO> queryOpenActivityStrategyAwardList();

}
