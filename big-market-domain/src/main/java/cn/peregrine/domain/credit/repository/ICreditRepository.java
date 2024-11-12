package cn.peregrine.domain.credit.repository;

import cn.peregrine.domain.credit.model.aggregate.TradeAggregate;

/**
 * @description 用户积分仓储
 */
public interface ICreditRepository {

    void saveUserCreditTradeOrder(TradeAggregate tradeAggregate);

}
