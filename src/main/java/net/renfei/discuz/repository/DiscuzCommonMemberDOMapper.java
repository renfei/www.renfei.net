package net.renfei.discuz.repository;

import java.util.List;
import net.renfei.discuz.repository.entity.DiscuzCommonMemberDO;
import net.renfei.discuz.repository.entity.DiscuzCommonMemberDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscuzCommonMemberDOMapper {
    long countByExample(DiscuzCommonMemberDOExample example);

    int deleteByExample(DiscuzCommonMemberDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzCommonMemberDO record);

    int insertSelective(DiscuzCommonMemberDO record);

    List<DiscuzCommonMemberDO> selectByExample(DiscuzCommonMemberDOExample example);

    DiscuzCommonMemberDO selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzCommonMemberDO record, @Param("example") DiscuzCommonMemberDOExample example);

    int updateByExample(@Param("record") DiscuzCommonMemberDO record, @Param("example") DiscuzCommonMemberDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonMemberDO record);

    int updateByPrimaryKey(DiscuzCommonMemberDO record);
}