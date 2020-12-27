package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.SecretKeyDO;
import net.renfei.repository.entity.SecretKeyDOExample;
import net.renfei.repository.entity.SecretKeyDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretKeyDOMapper {
    long countByExample(SecretKeyDOExample example);

    int deleteByExample(SecretKeyDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SecretKeyDOWithBLOBs record);

    int insertSelective(SecretKeyDOWithBLOBs record);

    List<SecretKeyDOWithBLOBs> selectByExampleWithBLOBs(SecretKeyDOExample example);

    List<SecretKeyDO> selectByExample(SecretKeyDOExample example);

    SecretKeyDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SecretKeyDOWithBLOBs record, @Param("example") SecretKeyDOExample example);

    int updateByExampleWithBLOBs(@Param("record") SecretKeyDOWithBLOBs record, @Param("example") SecretKeyDOExample example);

    int updateByExample(@Param("record") SecretKeyDO record, @Param("example") SecretKeyDOExample example);

    int updateByPrimaryKeySelective(SecretKeyDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SecretKeyDOWithBLOBs record);

    int updateByPrimaryKey(SecretKeyDO record);
}