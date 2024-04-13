package cn.peregrine.domain.strategy.service.rule.chain;
/**
 * @author: Peregrine Calder
 * @description: 装配接口
 * @version: 1.0
 */
public interface ILogicChainArmory {
    ILogicChain appendNext(ILogicChain next);
    ILogicChain next();
}
