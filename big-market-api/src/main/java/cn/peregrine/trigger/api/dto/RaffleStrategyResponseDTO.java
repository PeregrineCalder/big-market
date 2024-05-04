package cn.peregrine.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: RaffleResponseDTO
 * @author: Peregrine Calder
 * @description: 抽奖应答结果
 * @date: 2024/4/18 18:00
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleStrategyResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 排序编号 (策略奖品配置的奖品顺序编号)
    private Integer awardIndex;
}
