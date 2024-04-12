package cn.peregrine.domain.strategy.service.rule.factory;

import cn.peregrine.domain.strategy.model.entity.RuleActionEntity;
import cn.peregrine.domain.strategy.service.annotation.LogicStrategy;
import cn.peregrine.domain.strategy.service.rule.ILogicFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.rule.factory
 * @className: DefaultLogicFactory
 * @author: Peregrine Calder
 * @description: 规则工厂
 * @date: 2024/4/12 16:44
 * @version: 1.0
 */
@Service
public class DefaultLogicFactory {
    public Map<String, ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();
    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters) {
        logicFilters.forEach(logic -> {
            LogicStrategy strategy = AnnotationUtils.findAnnotation(logic.getClass(), LogicStrategy.class);
            if (strategy != null) {
                logicFilterMap.put(strategy.logicModel().getCode(), logic);
            }
        });
    }
    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        return (Map<String, ILogicFilter<T>>) (Map<?,?>) logicFilterMap;
    }
    @Getter
    @AllArgsConstructor
    public enum LogicModel {
        RULE_WIGHT("rule_weight","抽奖前规则 - 根据抽奖权重返回可抽奖范围KEY"),
        RULE_BLACKLIST("rule_blacklist","抽奖前规则 - 黑名单规则过滤，命中黑名单则直接返回"),
        ;
        private final String code;
        private final String info;
    }

}
