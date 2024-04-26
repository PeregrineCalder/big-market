package cn.peregrine.test.domain.strategy;

import cn.peregrine.domain.strategy.service.armory.IStrategyArmory;
import cn.peregrine.domain.strategy.service.rule.chain.ILogicChain;
import cn.peregrine.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.peregrine.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.domain
 * @className: LogicChainTest
 * @author: Peregrine Calder
 * @description: TODO
 * @date: 2024/4/14 02:09
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicChainTest {
    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;
    @Resource
    private DefaultChainFactory defaultChainFactory;
    @Before
    public void setUp() {
        // 策略装配
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100001L));
    }
    @Test
    public void test_LogicChain_rule_blacklist() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO strategyAwardVO = logicChain.logic("user001", 100001L);
        log.info("测试结果：{}", strategyAwardVO.getAwardId());
    }

    @Test
    public void test_LogicChain_rule_weight() {
        // 通过反射 mock 规则中的值
        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore", 4900L);
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO strategyAwardVO = logicChain.logic("peregrine", 100001L);
        log.info("测试结果：{}", strategyAwardVO.getAwardId());
    }

    @Test
    public void test_LogicChain_rule_default() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO strategyAwardVO = logicChain.logic("peregrine", 100001L);
        log.info("测试结果：{}", strategyAwardVO.getAwardId());
    }
}
