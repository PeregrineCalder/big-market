package cn.peregrine.trigger.api.dto;

import lombok.Data;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: ActivityDrawRequestDTO
 * @author: Peregrine Calder
 * @description: 活动抽奖请求对象
 * @date: 2024/5/2 15:59
 * @version: 1.0
 */
@Data
public class ActivityDrawRequestDTO {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;
}
