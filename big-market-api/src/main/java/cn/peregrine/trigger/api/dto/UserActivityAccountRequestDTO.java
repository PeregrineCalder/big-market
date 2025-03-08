package cn.peregrine.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: UserActivityAccountRequestDTO
 * @author: Peregrine Calder
 * @description: 用户活动账户请求对象
 * @date: 2024/8/28 09:21
 * @version: 1.0
 */
@Data
public class UserActivityAccountRequestDTO implements Serializable {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;
}
