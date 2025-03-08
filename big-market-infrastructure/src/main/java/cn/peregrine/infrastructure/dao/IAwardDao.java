package cn.peregrine.infrastructure.dao;

import cn.peregrine.infrastructure.dao.po.Award;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();

    String queryAwardConfigByAwardId(Integer awardId);

    String queryAwardKeyByAwardId(Integer awardId);
}

