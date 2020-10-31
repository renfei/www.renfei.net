package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.PhotoImgDO;
import net.renfei.repository.entity.PhotoImgDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoImgDOMapper {
    long countByExample(PhotoImgDOExample example);

    int deleteByExample(PhotoImgDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PhotoImgDO record);

    int insertSelective(PhotoImgDO record);

    List<PhotoImgDO> selectByExampleWithBLOBs(PhotoImgDOExample example);

    List<PhotoImgDO> selectByExample(PhotoImgDOExample example);

    PhotoImgDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PhotoImgDO record, @Param("example") PhotoImgDOExample example);

    int updateByExampleWithBLOBs(@Param("record") PhotoImgDO record, @Param("example") PhotoImgDOExample example);

    int updateByExample(@Param("record") PhotoImgDO record, @Param("example") PhotoImgDOExample example);

    int updateByPrimaryKeySelective(PhotoImgDO record);

    int updateByPrimaryKeyWithBLOBs(PhotoImgDO record);

    int updateByPrimaryKey(PhotoImgDO record);
}