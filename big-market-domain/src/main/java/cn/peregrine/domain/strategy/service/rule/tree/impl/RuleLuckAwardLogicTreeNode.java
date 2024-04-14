package cn.peregrine.domain.strategy.service.rule.tree.impl;

import cn.peregrine.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.peregrine.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.peregrine.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.rule.tree.impl
 * @className: RuleLuckAwardLogicTreeNode
 * @author: Peregrine Calder
 * @description: 兜底奖励节点
 * @date: 2024/4/14 16:26
 * @version: 1.0
 */
@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardData(DefaultTreeFactory.StrategyAwardData.builder()
                        .awardId(awardId)
                        .awardRuleValue("1,100")
                        .build())
                .build();
    }
}
