package cn.peregrine.domain.activity.service.quota.rule;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service.rule
 * @className: AbstractActionChain
 * @author: Peregrine Calder
 * @description: 下单规则责任链抽象类
 * @date: 2024/4/26 23:04
 * @version: 1.0
 */
public abstract class AbstractActionChain implements IActionChain{
    private IActionChain next;
    @Override
    public IActionChain next() {
        return next;
    }
    @Override
    public IActionChain appendNext(IActionChain next) {
        this.next = next;
        return next;
    }
}
