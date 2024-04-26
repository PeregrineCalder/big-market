package cn.peregrine.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.model.entity
 * @className: ActivitySkuEntity
 * @author: Peregrine Calder
 * @description: 活动 sku 实体对象
 * @date: 2024/4/25 23:19
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuEntity {
    /** 商品sku */
    private Long sku;
    /** 活动ID */
    private Long activityId;
    /** 活动个人参数ID；在这个活动上，一个人可参与多少次活动（总、日、月） */
    private Long activityCountId;
    /** 库存总量 */
    private Integer stockCount;
    /** 剩余库存 */
    private Integer stockCountSurplus;

}
