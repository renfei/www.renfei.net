package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.VideoDO;
import net.renfei.repository.entity.VideoDOExample;
import net.renfei.repository.entity.VideoDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDOMapper {
    long countByExample(VideoDOExample example);

    int deleteByExample(VideoDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VideoDOWithBLOBs record);

    int insertSelective(VideoDOWithBLOBs record);

    List<VideoDOWithBLOBs> selectByExampleWithBLOBs(VideoDOExample example);

    List<VideoDO> selectByExample(VideoDOExample example);

    VideoDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VideoDOWithBLOBs record, @Param("example") VideoDOExample example);

    int updateByExampleWithBLOBs(@Param("record") VideoDOWithBLOBs record, @Param("example") VideoDOExample example);

    int updateByExample(@Param("record") VideoDO record, @Param("example") VideoDOExample example);

    int updateByPrimaryKeySelective(VideoDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(VideoDOWithBLOBs record);

    int updateByPrimaryKey(VideoDO record);
}