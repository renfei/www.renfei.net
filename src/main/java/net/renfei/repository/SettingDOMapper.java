package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.SettingDO;
import net.renfei.repository.entity.SettingDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingDOMapper {
    long countByExample(SettingDOExample example);

    int deleteByExample(SettingDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SettingDO record);

    int insertSelective(SettingDO record);

    List<SettingDO> selectByExampleWithBLOBs(SettingDOExample example);

    List<SettingDO> selectByExample(SettingDOExample example);

    SettingDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SettingDO record, @Param("example") SettingDOExample example);

    int updateByExampleWithBLOBs(@Param("record") SettingDO record, @Param("example") SettingDOExample example);

    int updateByExample(@Param("record") SettingDO record, @Param("example") SettingDOExample example);

    int updateByPrimaryKeySelective(SettingDO record);

    int updateByPrimaryKeyWithBLOBs(SettingDO record);

    int updateByPrimaryKey(SettingDO record);
}