package cn.peregrine.domain.activity.service.rule;
/**
 * @description 抽奖动作责任链装配
 */
public interface IActionChainArmory {
    IActionChain next();
    IActionChain appendNext(IActionChain next);
}
