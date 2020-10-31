package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.LinkDOMapper;
import net.renfei.repository.entity.LinkDOExample;
import net.renfei.repository.entity.LinkDOWithBLOBs;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: LinkService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "LinkService")
public class LinkService extends BaseService {
    private final LinkDOMapper linkDOMapper;

    public LinkService(LinkDOMapper linkDOMapper) {
        this.linkDOMapper = linkDOMapper;
    }

    @Cacheable
    public List<LinkDOWithBLOBs> getLinks() {
        LinkDOExample example = new LinkDOExample();
        example.setOrderByClause("order_id ASC");
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andAuditPassEqualTo(true)
                .andLinkTypeEqualTo(1);
        return linkDOMapper.selectByExampleWithBLOBs(example);
    }
}
