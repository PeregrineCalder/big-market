package cn.peregrine.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.valobj
 * @className: StrategyAwardRuleModelVO
 * @author: Peregrine Calder
 * @description: 抽奖策略规则规则值对象, 值对象, 没有唯一 ID, 仅限于从数据库查询对象
 * @date: 2024/4/13 17:56
 * @version: 1.0
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {
    private String ruleModels;
}
