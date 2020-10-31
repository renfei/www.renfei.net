package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.VideoSourceDO;
import net.renfei.repository.entity.VideoSourceDOExample;
import net.renfei.repository.entity.VideoSourceDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSourceDOMapper {
    long countByExample(VideoSourceDOExample example);

    int deleteByExample(VideoSourceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VideoSourceDOWithBLOBs record);

    int insertSelective(VideoSourceDOWithBLOBs record);

    List<VideoSourceDOWithBLOBs> selectByExampleWithBLOBs(VideoSourceDOExample example);

    List<VideoSourceDO> selectByExample(VideoSourceDOExample example);

    VideoSourceDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VideoSourceDOWithBLOBs record, @Param("example") VideoSourceDOExample example);

    int updateByExampleWithBLOBs(@Param("record") VideoSourceDOWithBLOBs record, @Param("example") VideoSourceDOExample example);

    int updateByExample(@Param("record") VideoSourceDO record, @Param("example") VideoSourceDOExample example);

    int updateByPrimaryKeySelective(VideoSourceDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(VideoSourceDOWithBLOBs record);

    int updateByPrimaryKey(VideoSourceDO record);
}