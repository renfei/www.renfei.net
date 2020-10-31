package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.TypeDO;
import net.renfei.repository.entity.TypeDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDOMapper {
    long countByExample(TypeDOExample example);

    int deleteByExample(TypeDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TypeDO record);

    int insertSelective(TypeDO record);

    List<TypeDO> selectByExample(TypeDOExample example);

    TypeDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TypeDO record, @Param("example") TypeDOExample example);

    int updateByExample(@Param("record") TypeDO record, @Param("example") TypeDOExample example);

    int updateByPrimaryKeySelective(TypeDO record);

    int updateByPrimaryKey(TypeDO record);
}