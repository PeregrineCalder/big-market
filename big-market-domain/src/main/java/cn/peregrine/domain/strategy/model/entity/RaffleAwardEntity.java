package cn.peregrine.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.entity
 * @className: RaffleAwardEntity
 * @author: Peregrine Calder
 * @description: 抽奖奖品实体
 * @date: 2024/4/12 15:43
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {
    /** 奖品ID */
    private Integer awardId;
    /** 奖品配置信息 */
    private String awardConfig;
    /** 奖品顺序号 */
    private Integer sort;
}
