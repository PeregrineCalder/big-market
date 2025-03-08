package cn.peregrine.infrastructure.dao;

import cn.peregrine.infrastructure.dao.po.UserCreditAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 用户积分账户
 */
@Mapper
public interface IUserCreditAccountDao {

    void insert(UserCreditAccount userCreditAccountReq);

    int updateAddAmount(UserCreditAccount userCreditAccountReq);

    UserCreditAccount queryUserCreditAccount(UserCreditAccount userCreditAccountReq);

}

