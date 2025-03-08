package cn.peregrine.domain.strategy.service.armory.algorithm.impl;

import cn.peregrine.domain.strategy.model.entity.StrategyAwardEntity;
import cn.peregrine.domain.strategy.service.armory.algorithm.AbstractAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.armory.algorithm.impl
 * @className: O1Algorithm
 * @author: Peregrine Calder
 * @version: 1.0
 */
@Slf4j
@Component("o1Algorithm")
public class O1Algorithm extends AbstractAlgorithm {


    @Override
    public void armoryAlgorithm(String key, List<StrategyAwardEntity> strategyAwardEntities, BigDecimal rateRange) {
        log.info("抽奖算法 O(1) 装配 key:{}", key);
        List<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            for (int i = 0; i < rateRange.multiply(awardRate).intValue(); i++) {
                strategyAwardSearchRateTables.add(awardId);
            }
        }

        Collections.shuffle(strategyAwardSearchRateTables);

        Map<Integer, Integer> shuffleStrategyAwardSearchRateTable = new LinkedHashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardSearchRateTable.put(i, strategyAwardSearchRateTables.get(i));
        }

        repository.storeStrategyAwardSearchRateTable(key, shuffleStrategyAwardSearchRateTable.size(), shuffleStrategyAwardSearchRateTable);
    }

    @Override
    public Integer dispatchAlgorithm(String key) {
        log.info("抽奖算法 O(1) 抽奖计算 key:{}", key);
        int rateRange = repository.getRateRange(key);
        return repository.getStrategyAwardAssemble(key, secureRandom.nextInt(rateRange));
    }
}
