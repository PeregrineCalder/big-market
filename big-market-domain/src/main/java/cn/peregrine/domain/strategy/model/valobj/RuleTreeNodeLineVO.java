package cn.peregrine.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.valobj
 * @className: RuleTreeNodeLineVO
 * @author: Peregrine Calder
 * @description: 规则树节点指向线对象, 用于衔接 from -> to 节点链路关系
 * @date: 2024/4/14 16:12
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {
    /** 规则树ID */
    private Integer treeId;
    /** 规则 Key 节点 From */
    private String ruleNodeFrom;
    /** 规则 Key 节点 To */
    private String ruleNodeTo;
    /** 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围] */
    private RuleLimitTypeVO ruleLimitType;
    /** 限定值（到下个节点） */
    private RuleLogicCheckTypeVO ruleLimitValue;

}
