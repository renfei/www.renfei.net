package net.renfei.discuz.repository;

import java.util.List;
import net.renfei.discuz.repository.entity.DiscuzCommonMemberFieldForumDO;
import net.renfei.discuz.repository.entity.DiscuzCommonMemberFieldForumDOExample;
import net.renfei.discuz.repository.entity.DiscuzCommonMemberFieldForumDOWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscuzCommonMemberFieldForumDOMapper {
    long countByExample(DiscuzCommonMemberFieldForumDOExample example);

    int deleteByExample(DiscuzCommonMemberFieldForumDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzCommonMemberFieldForumDOWithBLOBs record);

    int insertSelective(DiscuzCommonMemberFieldForumDOWithBLOBs record);

    List<DiscuzCommonMemberFieldForumDOWithBLOBs> selectByExampleWithBLOBs(DiscuzCommonMemberFieldForumDOExample example);

    List<DiscuzCommonMemberFieldForumDO> selectByExample(DiscuzCommonMemberFieldForumDOExample example);

    DiscuzCommonMemberFieldForumDOWithBLOBs selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzCommonMemberFieldForumDOWithBLOBs record, @Param("example") DiscuzCommonMemberFieldForumDOExample example);

    int updateByExampleWithBLOBs(@Param("record") DiscuzCommonMemberFieldForumDOWithBLOBs record, @Param("example") DiscuzCommonMemberFieldForumDOExample example);

    int updateByExample(@Param("record") DiscuzCommonMemberFieldForumDO record, @Param("example") DiscuzCommonMemberFieldForumDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonMemberFieldForumDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DiscuzCommonMemberFieldForumDOWithBLOBs record);

    int updateByPrimaryKey(DiscuzCommonMemberFieldForumDO record);
}