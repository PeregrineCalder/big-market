package cn.peregrine.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: RaffleRequestDTO
 * @author: Peregrine Calder
 * @description: 抽奖请求参数
 * @date: 2024/4/18 17:59
 * @version: 2.0
 */
@Data
public class RaffleStrategyRequestDTO implements Serializable {
    // 抽奖策略ID
    private Long strategyId;
}
