package cn.peregrine.domain.activity.service.quota.policy.impl;

import cn.peregrine.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.peregrine.domain.activity.model.valobj.OrderStateVO;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import cn.peregrine.domain.activity.service.quota.policy.ITradePolicy;
import org.springframework.stereotype.Service;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service.quota.policy.impl
 * @className: CreditPayTradePolicy
 * @author: Peregrine Calder
 * @description: 积分兑换，支付类订单
 * @version: 1.0
 */
@Service("credit_pay_trade")
public class CreditPayTradePolicy implements ITradePolicy {

    private final IActivityRepository activityRepository;

    public CreditPayTradePolicy(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate) {
        createQuotaOrderAggregate.setOrderState(OrderStateVO.wait_pay);
        activityRepository.doSaveCreditPayOrder(createQuotaOrderAggregate);
    }

}

