package cn.peregrine.domain.strategy.service.rule.tree.factory.engine.impl;

import cn.peregrine.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.peregrine.domain.strategy.model.valobj.RuleTreeNodeLineVO;
import cn.peregrine.domain.strategy.model.valobj.RuleTreeNodeVO;
import cn.peregrine.domain.strategy.model.valobj.RuleTreeVO;
import cn.peregrine.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.peregrine.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.peregrine.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.rule.tree.factory.engine.impl
 * @className: DecisionTreeEngine
 * @author: Peregrine Calder
 * @description: 决策树引擎
 * @date: 2024/4/14 16:35
 * @version: 1.0
 */
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {
    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;
    private final RuleTreeVO ruleTreeVO;
    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }
    @Override
    public DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId) {
        DefaultTreeFactory.StrategyAwardVO strategyAwardVO = null;
        // 获取基础信息
        String nextNode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();
        // 获取起始节点 (根节点记录了第一个要执行的规则)
        RuleTreeNodeVO ruleTreeNode = treeNodeMap.get(nextNode);
        while (nextNode != null) {
            // 获取决策节点
            ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(ruleTreeNode.getRuleKey());
            String ruleValue = ruleTreeNode.getRuleValue();
            // 决策节点计算
            DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId, strategyId, awardId, ruleValue);
            RuleLogicCheckTypeVO ruleLogicCheckType = logicEntity.getRuleLogicCheckType();
            strategyAwardVO = logicEntity.getStrategyAwardVO();
            log.info("决策树引擎【{}】treeId:{} node:{} code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(), nextNode, ruleLogicCheckType.getCode());
            // 获取下个节点
            nextNode = nextNode(ruleLogicCheckType.getCode(), ruleTreeNode.getTreeNodeLineVOList());
            ruleTreeNode = treeNodeMap.get(nextNode);
        }
        // 返回最终结果
        return strategyAwardVO;
    }
    private String nextNode(String matterValue, List<RuleTreeNodeLineVO> ruleTreeNodeLineVOList) {
        if (ruleTreeNodeLineVOList == null || ruleTreeNodeLineVOList.isEmpty()) {
            return null;
        }
        for (RuleTreeNodeLineVO ruleTreeNodeLineVO : ruleTreeNodeLineVOList) {
            if (decisionLogic(matterValue, ruleTreeNodeLineVO)) {
                return ruleTreeNodeLineVO.getRuleNodeTo();
            }
        }
        throw new RuntimeException("决策树引擎 nextNode 计算失败, 未找到可执行节点");
    }
    public boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            // 以下规则暂时不需要实现
            case GT:
            case LT:
            case GE:
            case LE:
            default:
                return false;
        }
    }

}
