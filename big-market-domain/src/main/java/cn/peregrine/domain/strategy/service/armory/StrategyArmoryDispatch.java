package cn.peregrine.domain.strategy.service.armory;

import cn.peregrine.domain.strategy.model.entity.StrategyAwardEntity;
import cn.peregrine.domain.strategy.service.armory.algorithm.AbstractAlgorithm;
import cn.peregrine.domain.strategy.service.armory.algorithm.IAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.armory
 * @className: StrategyArmory
 * @author: Peregrine Calder
 * @description: 策略装配库，负责初始化策略计算
 * @date: 2024/4/9 17:42
 * @version: 1.0
 */
@Slf4j
@Service("strategyArmoryDispatch")
public class StrategyArmoryDispatch extends AbstractStrategyAlgorithm{

    private final Map<String, IAlgorithm> algorithmMap;
    // 抽奖算法阈值，在多少范围内开始选择不同选择
    private final Integer ALGORITHM_THRESHOLD_VALUE = 10000;

    public StrategyArmoryDispatch(Map<String, IAlgorithm> algorithmMap) {
        this.algorithmMap = algorithmMap;
    }


    @Override
    protected void armoryAlgorithm(String key, List<StrategyAwardEntity> strategyAwardEntities) {
        BigDecimal minAwardRate = minAwardRate(strategyAwardEntities);
        double rateRange = convert(minAwardRate.doubleValue());
        if (rateRange <= ALGORITHM_THRESHOLD_VALUE) {
            IAlgorithm o1Algorithm = algorithmMap.get(AbstractAlgorithm.Algorithm.O1.getKey());
            o1Algorithm.armoryAlgorithm(key, strategyAwardEntities, new BigDecimal(rateRange));
            repository.cacheStrategyArmoryAlgorithm(key, AbstractAlgorithm.Algorithm.O1.getKey());
        } else {
            IAlgorithm oLogNAlgorithm = algorithmMap.get(AbstractAlgorithm.Algorithm.OLogN.getKey());
            oLogNAlgorithm.armoryAlgorithm(key, strategyAwardEntities, new BigDecimal(rateRange));
            repository.cacheStrategyArmoryAlgorithm(key, AbstractAlgorithm.Algorithm.OLogN.getKey());
        }
    }

    @Override
    protected Integer dispatchAlgorithm(String key) {
        String beanName = repository.queryStrategyArmoryAlgorithmFromCache(key);
        if (null == beanName) throw new RuntimeException("key " + key + " beanName is " + beanName);
        IAlgorithm algorithm = algorithmMap.get(beanName);
        return algorithm.dispatchAlgorithm(key);
    }
}
