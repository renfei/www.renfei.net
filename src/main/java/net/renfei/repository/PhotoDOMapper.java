package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.PhotoDO;
import net.renfei.repository.entity.PhotoDOExample;
import net.renfei.repository.entity.PhotoDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDOMapper {
    long countByExample(PhotoDOExample example);

    int deleteByExample(PhotoDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhotoDOWithBLOBs record);

    int insertSelective(PhotoDOWithBLOBs record);

    List<PhotoDOWithBLOBs> selectByExampleWithBLOBs(PhotoDOExample example);

    List<PhotoDO> selectByExample(PhotoDOExample example);

    PhotoDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhotoDOWithBLOBs record, @Param("example") PhotoDOExample example);

    int updateByExampleWithBLOBs(@Param("record") PhotoDOWithBLOBs record, @Param("example") PhotoDOExample example);

    int updateByExample(@Param("record") PhotoDO record, @Param("example") PhotoDOExample example);

    int updateByPrimaryKeySelective(PhotoDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PhotoDOWithBLOBs record);

    int updateByPrimaryKey(PhotoDO record);
}