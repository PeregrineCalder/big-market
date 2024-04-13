package cn.peregrine.domain.strategy.service.rule.chain.factory;

import cn.peregrine.domain.strategy.model.entity.StrategyEntity;
import cn.peregrine.domain.strategy.repository.IStrategyRepository;
import cn.peregrine.domain.strategy.service.rule.chain.ILogicChain;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.rule.chain.factory
 * @className: DefaultChainFactory
 * @author: Peregrine Calder
 * @description: 工厂
 * @date: 2024/4/14 01:43
 * @version: 1.0
 */
@Service
public class DefaultChainFactory {
    private final Map<String, ILogicChain> logicChainGroup;
    private IStrategyRepository repository;
    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }
    /**
     * 通过策略ID，构建责任链
     *
     * @param strategyId 策略ID
     * @return LogicChain
     */
    public ILogicChain openLogicChain(Long strategyId) {
        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategy.ruleModels();
        if (ruleModels == null || ruleModels.length == 0) {
            return logicChainGroup.get("default");
        }
        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = logicChain;
        for (int i = 1; i < ruleModels.length; i++) {
            ILogicChain nextChain = logicChainGroup.get(ruleModels[i]);
            current = current.appendNext(nextChain);
        }
        current.appendNext(logicChainGroup.get("default"));
        return logicChain;
    }
}
