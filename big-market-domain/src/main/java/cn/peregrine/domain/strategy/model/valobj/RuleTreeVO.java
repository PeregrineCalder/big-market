package cn.peregrine.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.model.valobj
 * @className: RuleTreeVO
 * @author: Peregrine Calder
 * @description: 规则树对象 (不具有唯一ID, 不需要改变数据库结果的对象, 可以被定义为值对象)
 * @date: 2024/4/14 16:12
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeVO {
    /** 规则树ID */
    private String treeId;
    /** 规则树名称 */
    private String treeName;
    /** 规则树描述 */
    private String treeDesc;
    /** 规则根节点 */
    private String treeRootRuleNode;
    /** 规则节点 */
    private Map<String, RuleTreeNodeVO> treeNodeMap;

}
