package cn.peregrine.domain.strategy.service.rule.chain;
/**
 * @author: Peregrine Calder
 * @description: 抽奖策略规则责任链接口
 * @version: 1.0
 */
public interface ILogicChain extends ILogicChainArmory{
    /**
     * 责任链接口
     *
     * @param userId     用户ID
     * @param strategyId 策略ID
     * @return 奖品ID
     */
    Integer logic(String userId, Long strategyId);

}
