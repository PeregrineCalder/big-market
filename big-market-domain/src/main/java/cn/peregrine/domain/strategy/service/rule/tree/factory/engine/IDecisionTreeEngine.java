package cn.peregrine.domain.strategy.service.rule.tree.factory.engine;

import cn.peregrine.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author: Peregrine Calder
 * @description: 规则树组合接口
 * @version: 1.0
 */
public interface IDecisionTreeEngine {
    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
