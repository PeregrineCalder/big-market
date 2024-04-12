package cn.peregrine.domain.strategy.model.entity;

import lombok.Data;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.entity
 * @className: RuleMatterEntity
 * @author: Peregrine Calder
 * @description: 规则实体对象，用于过滤规则的必要参数信息
 * @date: 2024/4/12 15:50
 * @version: 1.0
 */
@Data
public class RuleMatterEntity {
    /** 用户ID */
    private String userId;
    /** 策略ID */
    private Long strategyId;
    /** 抽奖奖品 ID (规则类型为策略，则不需要奖品ID) */
    private Integer awardId;
    /** 抽奖规则类型 [rule_random - 随机值计算、rule_lock - 抽奖几次后解锁、rule_luck_award - 幸运奖(兜底奖品)] */
    private String ruleModel;
}
