package net.renfei.repository;

import net.renfei.repository.entity.HolidayDO;
import net.renfei.repository.entity.HolidayDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayDOMapper {
    long countByExample(HolidayDOExample example);

    int deleteByExample(HolidayDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HolidayDO record);

    int insertSelective(HolidayDO record);

    List<HolidayDO> selectByExample(HolidayDOExample example);

    HolidayDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HolidayDO record, @Param("example") HolidayDOExample example);

    int updateByExample(@Param("record") HolidayDO record, @Param("example") HolidayDOExample example);

    int updateByPrimaryKeySelective(HolidayDO record);

    int updateByPrimaryKey(HolidayDO record);
}