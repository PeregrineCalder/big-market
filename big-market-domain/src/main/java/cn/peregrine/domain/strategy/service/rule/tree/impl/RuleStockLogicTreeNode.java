package cn.peregrine.domain.strategy.service.rule.tree.impl;

import cn.peregrine.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.peregrine.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.peregrine.domain.strategy.repository.IStrategyRepository;
import cn.peregrine.domain.strategy.service.armory.IStrategyDispatch;
import cn.peregrine.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.peregrine.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.rule.tree.impl
 * @className: RuleStockLogicTreeNode
 * @author: Peregrine Calder
 * @description: 库存扣减节点
 * @date: 2024/4/14 16:27
 * @version: 1.0
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {
    @Resource
    private IStrategyDispatch strategyDispatch;
    @Resource
    private IStrategyRepository repository;
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue, Date endDateTime) {
        log.info("规则过滤-库存扣减 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        // 扣减库存
        Boolean status = strategyDispatch.subtractionAwardStock(strategyId, awardId, endDateTime);
        // true；库存扣减成功, TAKE_OVER 规则节点接管, 返回奖品ID, 奖品规则配置
        if (status) {
            log.info("规则过滤-库存扣减-成功 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
            // 写入延迟队列，延迟消费更新数据库记录 (在trigger的job: UpdateAwardStockJob 下消费队列，更新数据库记录)
            repository.awardStockConsumeSendQueue(StrategyAwardStockKeyVO.builder()
                    .strategyId(strategyId)
                    .awardId(awardId)
                    .build());
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                    .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                            .awardId(awardId)
                            .awardRuleValue(ruleValue)
                            .build())
                    .build();
        }
        // 如果库存不足, 直接返回放行
        log.warn("规则过滤-库存扣减-告警，库存不足。userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }
}
