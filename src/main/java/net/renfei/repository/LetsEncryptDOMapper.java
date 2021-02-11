package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.LetsEncryptDO;
import net.renfei.repository.entity.LetsEncryptDOExample;
import net.renfei.repository.entity.LetsEncryptDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LetsEncryptDOMapper {
    long countByExample(LetsEncryptDOExample example);

    int deleteByExample(LetsEncryptDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LetsEncryptDOWithBLOBs record);

    int insertSelective(LetsEncryptDOWithBLOBs record);

    List<LetsEncryptDOWithBLOBs> selectByExampleWithBLOBs(LetsEncryptDOExample example);

    List<LetsEncryptDO> selectByExample(LetsEncryptDOExample example);

    LetsEncryptDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LetsEncryptDOWithBLOBs record, @Param("example") LetsEncryptDOExample example);

    int updateByExampleWithBLOBs(@Param("record") LetsEncryptDOWithBLOBs record, @Param("example") LetsEncryptDOExample example);

    int updateByExample(@Param("record") LetsEncryptDO record, @Param("example") LetsEncryptDOExample example);

    int updateByPrimaryKeySelective(LetsEncryptDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LetsEncryptDOWithBLOBs record);

    int updateByPrimaryKey(LetsEncryptDO record);
}