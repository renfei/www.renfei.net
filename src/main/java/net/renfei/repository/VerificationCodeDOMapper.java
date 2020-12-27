package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.VerificationCodeDO;
import net.renfei.repository.entity.VerificationCodeDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeDOMapper {
    long countByExample(VerificationCodeDOExample example);

    int deleteByExample(VerificationCodeDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VerificationCodeDO record);

    int insertSelective(VerificationCodeDO record);

    List<VerificationCodeDO> selectByExample(VerificationCodeDOExample example);

    VerificationCodeDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VerificationCodeDO record, @Param("example") VerificationCodeDOExample example);

    int updateByExample(@Param("record") VerificationCodeDO record, @Param("example") VerificationCodeDOExample example);

    int updateByPrimaryKeySelective(VerificationCodeDO record);

    int updateByPrimaryKey(VerificationCodeDO record);
}