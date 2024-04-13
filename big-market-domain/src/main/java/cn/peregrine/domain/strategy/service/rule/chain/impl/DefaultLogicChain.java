package cn.peregrine.domain.strategy.service.rule.chain.impl;

import cn.peregrine.domain.strategy.service.armory.IStrategyDispatch;
import cn.peregrine.domain.strategy.service.rule.chain.AbstractLogicChain;
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
    public Integer logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
        return awardId;
    }
    @Override
    protected String ruleModel() {
        return "default";
    }
}
