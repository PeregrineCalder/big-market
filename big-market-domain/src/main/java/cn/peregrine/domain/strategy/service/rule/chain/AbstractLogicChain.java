package cn.peregrine.domain.strategy.service.rule.chain;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.rule.chain
 * @className: AbstractLogicChain
 * @author: Peregrine Calder
 * @description: 抽奖策略责任链，判断抽奖策略 (如默认抽象、权重抽奖、黑名单抽奖)
 * @date: 2024/4/14 00:26
 * @version: 1.0
 */
public abstract class AbstractLogicChain implements ILogicChain{
    private ILogicChain next;

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }
    protected abstract String ruleModel();
}
