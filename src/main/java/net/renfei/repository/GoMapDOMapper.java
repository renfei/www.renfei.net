package net.renfei.repository;

import net.renfei.repository.entity.GoMapDO;
import net.renfei.repository.entity.GoMapDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoMapDOMapper {
    long countByExample(GoMapDOExample example);

    int deleteByExample(GoMapDOExample example);

    int deleteByPrimaryKey(String code);

    int insert(GoMapDO record);

    int insertSelective(GoMapDO record);

    List<GoMapDO> selectByExample(GoMapDOExample example);

    GoMapDO selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") GoMapDO record, @Param("example") GoMapDOExample example);

    int updateByExample(@Param("record") GoMapDO record, @Param("example") GoMapDOExample example);

    int updateByPrimaryKeySelective(GoMapDO record);

    int updateByPrimaryKey(GoMapDO record);
}