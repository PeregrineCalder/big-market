package cn.peregrine.domain.strategy.service.armory.algorithm.impl;

import cn.peregrine.domain.strategy.model.entity.StrategyAwardEntity;
import cn.peregrine.domain.strategy.service.armory.algorithm.AbstractAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.armory.algorithm.impl
 * @className: OLogNAlgorithm
 * @author: Peregrine Calder
 * @version: 1.0
 */
@Slf4j
@Component("oLogNAlgorithm")
public class OLogNAlgorithm extends AbstractAlgorithm {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void armoryAlgorithm(String key, List<StrategyAwardEntity> strategyAwardEntities, BigDecimal rateRange) {
        log.info("抽奖算法 OLog(n) 装配 key:{}", key);
        int from = 1;
        int to = 0;

        Map<Map<Integer, Integer>, Integer> table = new HashMap<>();
        for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            to += rateRange.multiply(awardRate).intValue();

            Map<Integer, Integer> ft = new HashMap<>();
            ft.put(from, to);
            table.put(ft, awardId);
            from = to + 1;
        }

        repository.storeStrategyAwardSearchRateTable(key, to, table);
    }

    @Override
    public Integer dispatchAlgorithm(String key) {
        int rateRange = repository.getRateRange(key);
        Map<Map<String, Integer>, Integer> table = repository.getMap(key);
        if (table.size() <= 8) {
            log.info("抽奖算法 OLog(n) 抽奖计算（循环） key:{}", key);
            return forSearch(secureRandom.nextInt(rateRange), table);
        } else if (table.size() <= 16) {
            log.info("抽奖算法 OLog(n) 抽奖计算（二分） key:{}", key);
            return binarySearch(secureRandom.nextInt(rateRange), table);
        } else {
            log.info("抽奖算法 OLog(n) 抽奖计算（多线程） key:{}", key);
            return threadSearch(secureRandom.nextInt(rateRange), table);
        }
    }

    private Integer forSearch(int rateKey, Map<Map<String, Integer>, Integer> table) {
        Integer awardId = null;
        for (Map.Entry<Map<String, Integer>, Integer> entry : table.entrySet()) {
            Map<String, Integer> rangeMap = entry.getKey();

            for (Map.Entry<String, Integer> range : rangeMap.entrySet()) {
                int start = Integer.parseInt(range.getKey());
                int end = range.getValue();

                if (rateKey >= start && rateKey <= end) {
                    awardId = entry.getValue();
                    break;
                }
            }

            if (awardId != null) {
                break;
            }
        }

        return awardId;
    }

    private Integer binarySearch(int rateKey, Map<Map<String, Integer>, Integer> table) {
        List<Map.Entry<Map<String, Integer>, Integer>> entries = new ArrayList<>(table.entrySet());
        entries.sort(Comparator.comparingInt(e -> Integer.parseInt(e.getKey().keySet().iterator().next())));

        int left = 0;
        int right = entries.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Map.Entry<Map<String, Integer>, Integer> entry = entries.get(mid);
            Map<String, Integer> rangeMap = entry.getKey();
            Map.Entry<String, Integer> range = rangeMap.entrySet().iterator().next();

            int start = Integer.parseInt(range.getKey());
            int end = range.getValue();

            if (rateKey < start) {
                right = mid - 1;
            } else if (rateKey > end) {
                left = mid + 1;
            } else {
                return entry.getValue();
            }
        }

        return null;
    }

    private Integer threadSearch(int rateKey, Map<Map<String, Integer>, Integer> table) {
        List<CompletableFuture<Map.Entry<Map<String, Integer>, Integer>>> futures = table.entrySet().stream()
                .map(entry -> CompletableFuture.supplyAsync(() -> {
                    Map<String, Integer> rangeMap = entry.getKey();
                    for (Map.Entry<String, Integer> rangeEntry : rangeMap.entrySet()) {
                        int start = Integer.parseInt(rangeEntry.getKey());
                        int end = rangeEntry.getValue();
                        if (rateKey >= start && rateKey <= end) {
                            return entry;
                        }
                    }
                    return null;
                }, threadPoolExecutor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allFutures.join();
            for (CompletableFuture<Map.Entry<Map<String, Integer>, Integer>> future : futures) {
                Map.Entry<Map<String, Integer>, Integer> result = future.getNow(null);
                if (result != null) {
                    return result.getValue();
                }
            }
        } catch (CompletionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
