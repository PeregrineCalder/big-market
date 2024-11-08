package cn.peregrine.domain.rebate.model.entity;

import cn.peregrine.domain.rebate.model.valobj.BehaviorTypeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.rebate.model.entity
 * @className: BehaviorEntity
 * @author: Peregrine Calder
 * @description: 行为实体对象
 * @date: 2024/8/6 23:13
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 行为类型；sign 签到、openai_pay 支付
     */
    private BehaviorTypeVO behaviorTypeVO;
    /**
     * 业务ID；签到则是日期字符串，支付则是外部的业务ID
     */
    private String outBusinessNo;
}
