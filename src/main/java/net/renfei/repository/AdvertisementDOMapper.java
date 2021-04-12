package net.renfei.repository;

import net.renfei.repository.entity.AdvertisementDO;
import net.renfei.repository.entity.AdvertisementDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementDOMapper {
    long countByExample(AdvertisementDOExample example);

    int deleteByExample(AdvertisementDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdvertisementDO record);

    int insertSelective(AdvertisementDO record);

    List<AdvertisementDO> selectByExampleWithBLOBs(AdvertisementDOExample example);

    List<AdvertisementDO> selectByExample(AdvertisementDOExample example);

    AdvertisementDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdvertisementDO record, @Param("example") AdvertisementDOExample example);

    int updateByExampleWithBLOBs(@Param("record") AdvertisementDO record, @Param("example") AdvertisementDOExample example);

    int updateByExample(@Param("record") AdvertisementDO record, @Param("example") AdvertisementDOExample example);

    int updateByPrimaryKeySelective(AdvertisementDO record);

    int updateByPrimaryKeyWithBLOBs(AdvertisementDO record);

    int updateByPrimaryKey(AdvertisementDO record);
}