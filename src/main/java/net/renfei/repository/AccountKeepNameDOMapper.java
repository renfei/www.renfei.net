package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.AccountKeepNameDO;
import net.renfei.repository.entity.AccountKeepNameDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountKeepNameDOMapper {
    long countByExample(AccountKeepNameDOExample example);

    int deleteByExample(AccountKeepNameDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountKeepNameDO record);

    int insertSelective(AccountKeepNameDO record);

    List<AccountKeepNameDO> selectByExample(AccountKeepNameDOExample example);

    AccountKeepNameDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountKeepNameDO record, @Param("example") AccountKeepNameDOExample example);

    int updateByExample(@Param("record") AccountKeepNameDO record, @Param("example") AccountKeepNameDOExample example);

    int updateByPrimaryKeySelective(AccountKeepNameDO record);

    int updateByPrimaryKey(AccountKeepNameDO record);
}