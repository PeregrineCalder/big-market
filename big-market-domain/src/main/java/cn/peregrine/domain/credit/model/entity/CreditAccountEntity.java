package cn.peregrine.domain.credit.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.credit.model.entity
 * @className: CreditAccountEntity
 * @author: Peregrine Calder
 * @description: 积分账户实体
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccountEntity {

    /** 用户ID */
    private String userId;
    /** 可用积分，每次扣减的值 */
    private BigDecimal adjustAmount;

}

