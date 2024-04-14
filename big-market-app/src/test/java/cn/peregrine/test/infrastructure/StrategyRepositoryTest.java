package cn.peregrine.test.infrastructure;

import cn.peregrine.domain.strategy.model.valobj.RuleTreeVO;
import cn.peregrine.domain.strategy.repository.IStrategyRepository;
import cn.peregrine.infrastructure.persistent.po.RuleTreeNode;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.infrastructure
 * @className: StrategyRepositoryTest
 * @author: Peregrine Calder
 * @description: 仓储数据查询
 * @date: 2024/4/14 19:07
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyRepositoryTest {
    @Resource
    private IStrategyRepository strategyRepository;
    @Test
    public void queryRuleTreeVOByTreeId() {
        RuleTreeVO ruleTreeVO = strategyRepository.queryRuleTreeVOByTreeId("tree_lock");
        log.info("测试结果：{}", JSON.toJSONString(ruleTreeVO));
    }
}
