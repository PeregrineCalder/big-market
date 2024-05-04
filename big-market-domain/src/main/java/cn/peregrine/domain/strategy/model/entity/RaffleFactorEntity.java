package cn.peregrine.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.entity
 * @className: RaffleFactorEntity
 * @author: Peregrine Calder
 * @description: 抽奖因子实体
 * @date: 2024/4/12 15:40
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {
    /** 用户ID */
    private String userId;
    /** 策略ID */
    private Long strategyId;
    /** 结束时间 */
    private Date endDateTime;

}
