package cn.peregrine.domain.strategy.model.entity;

import cn.peregrine.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.entity
 * @className: RuleActionEntity
 * @author: Peregrine Calder
 * @description: 规则动作实体 - 影响后续动作变化
 * @date: 2024/4/12 15:55
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {
    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;

    static public class RaffleEntity {

    }
    // 抽奖前
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity {
        /** 策略 ID */
        private Long strategyId;
        /** 权重值 Key, 用于抽奖时可以选择权重抽奖 */
        private String ruleWeightValueKey;
        /** 奖品 ID */
        private Integer awardId;
    }
    // 抽奖中
    static public class RaffleCenterEntity extends RaffleEntity {

    }
    // 抽奖后
    static public class RaffleAfterEntity extends RaffleEntity {

    }



}
