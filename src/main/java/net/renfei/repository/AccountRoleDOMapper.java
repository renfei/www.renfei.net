package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.AccountRoleDO;
import net.renfei.repository.entity.AccountRoleDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleDOMapper {
    long countByExample(AccountRoleDOExample example);

    int deleteByExample(AccountRoleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountRoleDO record);

    int insertSelective(AccountRoleDO record);

    List<AccountRoleDO> selectByExample(AccountRoleDOExample example);

    AccountRoleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountRoleDO record, @Param("example") AccountRoleDOExample example);

    int updateByExample(@Param("record") AccountRoleDO record, @Param("example") AccountRoleDOExample example);

    int updateByPrimaryKeySelective(AccountRoleDO record);

    int updateByPrimaryKey(AccountRoleDO record);
}