package cn.peregrine.domain.strategy.service.rule.chain.impl;

import cn.peregrine.domain.strategy.service.armory.IStrategyDispatch;
import cn.peregrine.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.peregrine.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.peregrine.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.rule.chain.impl
 * @className: DefaultLogicChain
 * @author: Peregrine Calder
 * @description: 默认的责任链(作为最后一个链)
 * @date: 2024/4/14 00:33
 * @version: 1.0
 */
@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {
    @Resource
    protected IStrategyDispatch strategyDispatch;
    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
        return DefaultChainFactory.StrategyAwardVO.builder()
                .awardId(awardId)
                .logicModel(ruleModel())
                .build();
    }
    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_DEFAULT.getCode();
    }
}
