package cn.peregrine.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.award.model.entity
 * @className: DistributeAwardEntity
 * @author: Peregrine Calder
 * @description: 分发奖品实体
 * @version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistributeAwardEntity {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 奖品ID
     */
    private Integer awardId;
    /**
     * 奖品配置信息
     */
    private String awardConfig;

}

