package cn.peregrine.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.valobj
 * @className: StrategyAwardStockKeyVO
 * @author: Peregrine Calder
 * @description: 策略奖品库存 Key 标识值对象 (一般没有唯一 ID 且不影响数据库变化的对象被定义为值对象)
 * @date: 2024/4/16 00:49
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    // 策略ID
    private Long strategyId;
    // 奖品ID
    private Integer awardId;
}
