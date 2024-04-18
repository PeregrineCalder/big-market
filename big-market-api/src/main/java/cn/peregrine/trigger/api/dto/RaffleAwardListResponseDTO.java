package cn.peregrine.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: RaffleAwardListResponseDTO
 * @author: Peregrine Calder
 * @description: 抽奖奖品列表, 应答对象
 * @date: 2024/4/18 17:56
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardListResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 奖品标题
    private String awardTitle;
    // 奖品副标题
    private String awardSubtitle;
    // 排序编号
    private Integer sort;
}
