package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.repository.PageDOMapper;
import net.renfei.repository.entity.PageDOExample;
import net.renfei.repository.entity.PageDOWithBLOBs;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: PageService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "PageService")
public class PageService extends BaseService {
    private final PageDOMapper pageDOMapper;

    public PageService(PageDOMapper pageDOMapper) {
        this.pageDOMapper = pageDOMapper;
    }

    public List<PageDOWithBLOBs> getAllPageNotCache() {
        PageDOExample example = new PageDOExample();
        example.setOrderByClause("release_time DESC");
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return pageDOMapper.selectByExampleWithBLOBs(example);
    }

    public PageDOWithBLOBs getPageByID(String id) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
                return pageDOMapper.selectByPrimaryKey(ID);
            } catch (NumberFormatException nfe) {
                return null;
            }
        } else {
            return null;
        }
    }
}
