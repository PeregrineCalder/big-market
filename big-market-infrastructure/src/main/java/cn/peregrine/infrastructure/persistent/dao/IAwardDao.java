package cn.peregrine.infrastructure.persistent.dao;

import cn.peregrine.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
