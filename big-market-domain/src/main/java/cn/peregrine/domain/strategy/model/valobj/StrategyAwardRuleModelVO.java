package cn.peregrine.domain.strategy.model.valobj;

import cn.peregrine.domain.strategy.service.rule.factory.DefaultLogicFactory;
import cn.peregrine.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    public String[] raffleCenterRuleModelList() {
        List<String> ruleModelList = new ArrayList<>();
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        for (String ruleModelValue: ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isCenter(ruleModelValue)) {
                ruleModelList.add(ruleModelValue);
            }
        }
        return ruleModelList.toArray(new String[ruleModelList.size()]);
    }
    public String[] raffleAfterRuleModelList() {
        List<String> ruleModelList = new ArrayList<>();
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isAfter(ruleModelValue)) {
                ruleModelList.add(ruleModelValue);
            }
        }
        return ruleModelList.toArray(new String[0]);
    }
}
