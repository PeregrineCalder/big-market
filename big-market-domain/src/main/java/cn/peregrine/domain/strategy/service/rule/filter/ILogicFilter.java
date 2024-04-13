package cn.peregrine.domain.strategy.service.rule.filter;

import cn.peregrine.domain.strategy.model.entity.RuleActionEntity;
import cn.peregrine.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @author: Peregrine Calder
 * @description: 抽奖规则过滤接口
 * @version: 1.0
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
