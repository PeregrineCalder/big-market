package cn.peregrine.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: RaffleAwardListRequestDTO
 * @author: Peregrine Calder
 * @description: 抽奖奖品列表, 请求对象
 * @date: 2024/4/18 17:55
 * @version: 1.0
 */
@Data
public class RaffleAwardListRequestDTO implements Serializable {
    // 用户ID
    private String userId;
    // 抽奖活动ID
    private Long activityId;
}
