package cn.peregrine.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.entity
 * @className: StrategyAwardEntity
 * @author: Peregrine Calder
 * @description: 策略奖品实体
 * @date: 2024/4/9 17:52
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /** 抽奖奖品标题 */
    private String awardTitle;
    /** 抽奖奖品副标题 */
    private String awardSubtitle;
    /** 奖品库存总量 */
    private Integer awardCount;
    /** 奖品库存剩余 */
    private Integer awardCountSurplus;
    /** 奖品概率 */
    private BigDecimal awardRate;
    /** 排序 */
    private Integer sort;
}
