package cn.peregrine.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: RaffleStrategyRuleWeightRequestDTO
 * @author: Peregrine Calder
 * @description: 抽奖策略规则，权重配置，查询N次抽奖可解锁奖品范围，请求对象
 * @date: 2024/8/28 09:23
 * @version: 1.0
 */
@Data
public class RaffleStrategyRuleWeightRequestDTO implements Serializable {
    // 用户ID
    private String userId;
    // 抽奖活动ID
    private Long activityId;
}
