package net.renfei.repository;

import net.renfei.repository.entity.HotSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: CustomMapper</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Repository
public interface CustomMapper {
    /**
     * 获取热搜榜
     *
     * @return
     */
    List<HotSearch> getHotSearchList();
}
