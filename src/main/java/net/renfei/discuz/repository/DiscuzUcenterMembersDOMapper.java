package net.renfei.discuz.repository;

import java.util.List;
import net.renfei.discuz.repository.entity.DiscuzUcenterMembersDO;
import net.renfei.discuz.repository.entity.DiscuzUcenterMembersDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscuzUcenterMembersDOMapper {
    long countByExample(DiscuzUcenterMembersDOExample example);

    int deleteByExample(DiscuzUcenterMembersDOExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(DiscuzUcenterMembersDO record);

    int insertSelective(DiscuzUcenterMembersDO record);

    List<DiscuzUcenterMembersDO> selectByExample(DiscuzUcenterMembersDOExample example);

    DiscuzUcenterMembersDO selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") DiscuzUcenterMembersDO record, @Param("example") DiscuzUcenterMembersDOExample example);

    int updateByExample(@Param("record") DiscuzUcenterMembersDO record, @Param("example") DiscuzUcenterMembersDOExample example);

    int updateByPrimaryKeySelective(DiscuzUcenterMembersDO record);

    int updateByPrimaryKey(DiscuzUcenterMembersDO record);
}