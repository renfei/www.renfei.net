package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.PageDO;
import net.renfei.repository.entity.PageDOExample;
import net.renfei.repository.entity.PageDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageDOMapper {
    long countByExample(PageDOExample example);

    int deleteByExample(PageDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PageDOWithBLOBs record);

    int insertSelective(PageDOWithBLOBs record);

    List<PageDOWithBLOBs> selectByExampleWithBLOBs(PageDOExample example);

    List<PageDO> selectByExample(PageDOExample example);

    PageDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PageDOWithBLOBs record, @Param("example") PageDOExample example);

    int updateByExampleWithBLOBs(@Param("record") PageDOWithBLOBs record, @Param("example") PageDOExample example);

    int updateByExample(@Param("record") PageDO record, @Param("example") PageDOExample example);

    int updateByPrimaryKeySelective(PageDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PageDOWithBLOBs record);

    int updateByPrimaryKey(PageDO record);
}