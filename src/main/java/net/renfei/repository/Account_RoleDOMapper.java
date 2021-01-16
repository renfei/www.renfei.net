package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.Account_RoleDO;
import net.renfei.repository.entity.Account_RoleDOExample;
import org.apache.ibatis.annotations.Param;

public interface Account_RoleDOMapper {
    long countByExample(Account_RoleDOExample example);

    int deleteByExample(Account_RoleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Account_RoleDO record);

    int insertSelective(Account_RoleDO record);

    List<Account_RoleDO> selectByExample(Account_RoleDOExample example);

    Account_RoleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Account_RoleDO record, @Param("example") Account_RoleDOExample example);

    int updateByExample(@Param("record") Account_RoleDO record, @Param("example") Account_RoleDOExample example);

    int updateByPrimaryKeySelective(Account_RoleDO record);

    int updateByPrimaryKey(Account_RoleDO record);
}