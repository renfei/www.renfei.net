package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.CommentsDO;
import net.renfei.repository.entity.CommentsDOExample;
import net.renfei.repository.entity.CommentsDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsDOMapper {
    long countByExample(CommentsDOExample example);

    int deleteByExample(CommentsDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentsDOWithBLOBs record);

    int insertSelective(CommentsDOWithBLOBs record);

    List<CommentsDOWithBLOBs> selectByExampleWithBLOBs(CommentsDOExample example);

    List<CommentsDO> selectByExample(CommentsDOExample example);

    CommentsDOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommentsDOWithBLOBs record, @Param("example") CommentsDOExample example);

    int updateByExampleWithBLOBs(@Param("record") CommentsDOWithBLOBs record, @Param("example") CommentsDOExample example);

    int updateByExample(@Param("record") CommentsDO record, @Param("example") CommentsDOExample example);

    int updateByPrimaryKeySelective(CommentsDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CommentsDOWithBLOBs record);

    int updateByPrimaryKey(CommentsDO record);
}