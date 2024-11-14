package cn.peregrine.trigger.api.dto;

import lombok.Data;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.api.dto
 * @className: SkuProductShopCartRequestDTO
 * @author: Peregrine Calder
 * @description: 商品购物车请求对象
 * @version: 1.0
 */
@Data
public class SkuProductShopCartRequestDTO {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * sku 商品
     */
    private Long sku;

}

