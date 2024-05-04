package cn.peregrine.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: ActivityDrawResponseDTO
 * @author: Peregrine Calder
 * @description: 活动抽奖响应对象
 * @date: 2024/5/2 16:00
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDrawResponseDTO {
    // 奖品ID
    private Integer awardId;
    // 奖品标题
    private String awardTitle;
    // 排序编号【策略奖品配置的奖品顺序编号】
    private Integer awardIndex;

}
