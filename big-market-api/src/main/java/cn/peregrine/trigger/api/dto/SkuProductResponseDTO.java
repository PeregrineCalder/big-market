package cn.peregrine.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: SkuProductResponseDTO
 * @author: Peregrine Calder
 * @description: sku商品对象
 * @version: 1.0
 */
@Data
public class SkuProductResponseDTO implements Serializable {

    /**
     * 商品sku
     */
    private Long sku;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动个人参与次数ID
     */
    private Long activityCountId;
    /**
     * 库存总量
     */
    private Integer stockCount;
    /**
     * 剩余库存
     */
    private Integer stockCountSurplus;
    /**
     * 商品金额【积分】
     */
    private BigDecimal productAmount;

    /**
     * 活动商品数量
     */
    private ActivityCount activityCount;

    @Data
    public static class ActivityCount {
        /**
         * 总次数
         */
        private Integer totalCount;

        /**
         * 日次数
         */
        private Integer dayCount;

        /**
         * 月次数
         */
        private Integer monthCount;
    }

}

