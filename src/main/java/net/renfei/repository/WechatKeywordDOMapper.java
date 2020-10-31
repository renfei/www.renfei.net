package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.WechatKeywordDO;
import net.renfei.repository.entity.WechatKeywordDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WechatKeywordDOMapper {
    long countByExample(WechatKeywordDOExample example);

    int deleteByExample(WechatKeywordDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WechatKeywordDO record);

    int insertSelective(WechatKeywordDO record);

    List<WechatKeywordDO> selectByExample(WechatKeywordDOExample example);

    WechatKeywordDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WechatKeywordDO record, @Param("example") WechatKeywordDOExample example);

    int updateByExample(@Param("record") WechatKeywordDO record, @Param("example") WechatKeywordDOExample example);

    int updateByPrimaryKeySelective(WechatKeywordDO record);

    int updateByPrimaryKey(WechatKeywordDO record);
}