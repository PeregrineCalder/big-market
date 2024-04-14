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
        // 如果未配置策略规则, 则只装填一个默认责任链
        if (ruleModels == null || ruleModels.length == 0) {
            return logicChainGroup.get("default");
        }
        // 按照配置顺序装填用户配置的责任链: rule_blacklist、rule_weight (注意此数据从Redis缓存中获取，如果更新库表，记得在测试阶段手动处理缓存)
        ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = logicChain;
        for (int i = 1; i < ruleModels.length; i++) {
            ILogicChain nextChain = logicChainGroup.get(ruleModels[i]);
            current = current.appendNext(nextChain);
        }
        // 责任链的最后装填默认责任链
        current.appendNext(logicChainGroup.get("default"));
        return logicChain;
    }
}
