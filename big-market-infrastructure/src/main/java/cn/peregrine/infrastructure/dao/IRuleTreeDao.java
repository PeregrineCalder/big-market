package cn.peregrine.infrastructure.dao;

import cn.peregrine.infrastructure.dao.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 规则树表DAO
 */
@Mapper
public interface IRuleTreeDao {
    RuleTree queryRuleTreeByTreeId(String treeId);
}
