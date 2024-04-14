package cn.peregrine.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.infrastructure.persistent.po
 * @className: RuleTree
 * @author: Peregrine Calder
 * @description: 规则树
 * @date: 2024/4/14 18:46
 * @version: 1.0
 */
@Data
public class RuleTree {
    /** 自增ID */
    private Long id;
    /** 规则树ID */
    private String treeId;
    /** 规则树名称 */
    private String treeName;
    /** 规则树描述 */
    private String treeDesc;
    /** 规则根节点 */
    private String treeRootRuleKey;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
