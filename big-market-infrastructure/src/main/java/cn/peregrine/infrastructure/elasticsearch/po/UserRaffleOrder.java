package cn.peregrine.infrastructure.elasticsearch.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.infrastructure.elasticsearch.po
 * @className: UserRaffleOrder
 * @author: Peregrine Calder
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRaffleOrder {
    private String id;
    private String userId;
    private Long activityId;
    private String activityName;
    private Long strategyId;
    private String orderId;
    private Date orderTime;
    private String orderState;
    private Date createTime;
    private Date updateTime;
}
