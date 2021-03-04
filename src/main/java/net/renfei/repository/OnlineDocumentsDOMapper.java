package net.renfei.repository;

import java.util.List;
import java.util.Map;

import net.renfei.repository.entity.OnlineDocumentsDO;
import net.renfei.repository.entity.OnlineDocumentsDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineDocumentsDOMapper {
    long countByExample(OnlineDocumentsDOExample example);

    int deleteByExample(OnlineDocumentsDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OnlineDocumentsDO record);

    int insertSelective(OnlineDocumentsDO record);

    List<OnlineDocumentsDO> selectByExample(OnlineDocumentsDOExample example);

    OnlineDocumentsDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OnlineDocumentsDO record, @Param("example") OnlineDocumentsDOExample example);

    int updateByExample(@Param("record") OnlineDocumentsDO record, @Param("example") OnlineDocumentsDOExample example);

    int updateByPrimaryKeySelective(OnlineDocumentsDO record);

    int updateByPrimaryKey(OnlineDocumentsDO record);

    List<Map<String,Object>> selectCategory();
}