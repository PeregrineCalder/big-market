package cn.peregrine.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.model.valobj
 * @className: ActivitySkuStockKeyVO
 * @author: Peregrine Calder
 * @description: 活动 sku 库存 key 值对象
 * @date: 2024/4/27 21:32
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuStockKeyVO {
    /** 商品sku */
    private Long sku;
    /** 活动ID */
    private Long activityId;
}
