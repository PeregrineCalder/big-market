package cn.peregrine.domain.strategy.repository;

import cn.peregrine.domain.strategy.model.entity.StrategyAwardEntity;
import cn.peregrine.domain.strategy.model.entity.StrategyEntity;
import cn.peregrine.domain.strategy.model.entity.StrategyRuleEntity;

import java.util.List;
import java.util.Map;

/**
 * @author: Peregrine Calder
 * @description: 策略仓储接口
 * @version: 1.0
 */
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);
    void storeStrategyAwardSearchRateTable(String key, int rateRange, Map<Integer, Integer> strategyAwardSearchRateTable);
    int getRateRange(Long strategyId);
    int getRateRange(String key);
    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
    Integer getStrategyAwardAssemble(String key, int rateKey);
    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);
    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);
    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

}
