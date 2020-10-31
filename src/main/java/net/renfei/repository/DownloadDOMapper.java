package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.DownloadDO;
import net.renfei.repository.entity.DownloadDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadDOMapper {
    long countByExample(DownloadDOExample example);

    int deleteByExample(DownloadDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DownloadDO record);

    int insertSelective(DownloadDO record);

    List<DownloadDO> selectByExample(DownloadDOExample example);

    DownloadDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DownloadDO record, @Param("example") DownloadDOExample example);

    int updateByExample(@Param("record") DownloadDO record, @Param("example") DownloadDOExample example);

    int updateByPrimaryKeySelective(DownloadDO record);

    int updateByPrimaryKey(DownloadDO record);
}