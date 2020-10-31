package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.ShortUrl;
import net.renfei.repository.entity.ShortUrlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlMapper {
    long countByExample(ShortUrlExample example);

    int deleteByExample(ShortUrlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShortUrl record);

    int insertSelective(ShortUrl record);

    List<ShortUrl> selectByExample(ShortUrlExample example);

    ShortUrl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShortUrl record, @Param("example") ShortUrlExample example);

    int updateByExample(@Param("record") ShortUrl record, @Param("example") ShortUrlExample example);

    int updateByPrimaryKeySelective(ShortUrl record);

    int updateByPrimaryKey(ShortUrl record);
}