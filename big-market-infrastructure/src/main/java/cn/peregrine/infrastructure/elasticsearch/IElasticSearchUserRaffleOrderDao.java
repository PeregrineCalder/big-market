package cn.peregrine.infrastructure.elasticsearch;

import cn.peregrine.infrastructure.dao.po.UserRaffleOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IElasticSearchUserRaffleOrderDao {
    List<UserRaffleOrder> queryUserRaffleOrderList();
}
