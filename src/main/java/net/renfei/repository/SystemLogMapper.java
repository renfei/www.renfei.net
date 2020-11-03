package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.SystemLog;
import net.renfei.repository.entity.SystemLogExample;
import net.renfei.repository.entity.SystemLogWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogMapper {
    long countByExample(SystemLogExample example);

    int deleteByExample(SystemLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemLogWithBLOBs record);

    int insertSelective(SystemLogWithBLOBs record);

    List<SystemLogWithBLOBs> selectByExampleWithBLOBs(SystemLogExample example);

    List<SystemLog> selectByExample(SystemLogExample example);

    SystemLogWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemLogWithBLOBs record, @Param("example") SystemLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SystemLogWithBLOBs record, @Param("example") SystemLogExample example);

    int updateByExample(@Param("record") SystemLog record, @Param("example") SystemLogExample example);

    int updateByPrimaryKeySelective(SystemLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SystemLogWithBLOBs record);

    int updateByPrimaryKey(SystemLog record);
}