package cn.peregrine.domain.strategy.service.rule.tree;

import cn.peregrine.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author: Peregrine Calder
 * @description: 规则树接口
 * @version: 1.0
 */
public interface ILogicTreeNode {
    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);
}
