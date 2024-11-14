package cn.peregrine.domain.credit.repository;

import cn.peregrine.domain.credit.model.aggregate.TradeAggregate;
import cn.peregrine.domain.credit.model.entity.CreditAccountEntity;

/**
 * @description 用户积分仓储
 */
public interface ICreditRepository {

    void saveUserCreditTradeOrder(TradeAggregate tradeAggregate);

    CreditAccountEntity queryUserCreditAccount(String userId);
}
