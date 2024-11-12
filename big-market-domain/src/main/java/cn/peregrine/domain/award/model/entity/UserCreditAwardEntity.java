package cn.peregrine.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.award.model.entity
 * @className: UserCreditAwardEntity
 * @author: Peregrine Calder
 * @description: 用户积分奖品实体对象
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreditAwardEntity {

    /** 用户ID */
    private String userId;
    /** 积分值 */
    private BigDecimal creditAmount;

}
