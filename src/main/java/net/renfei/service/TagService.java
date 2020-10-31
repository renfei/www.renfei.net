package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.TagDOMapper;
import net.renfei.repository.TagRelationDOMapper;
import net.renfei.repository.entity.TagDO;
import net.renfei.repository.entity.TagDOExample;
import net.renfei.repository.entity.TagRelationDO;
import net.renfei.repository.entity.TagRelationDOExample;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: TagService</p>
 * <p>Description: 标签服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "TagService")
public class TagService extends BaseService {
    private final TagDOMapper tagDOMapper;
    private final TagRelationDOMapper tagRelationDOMapper;

    public TagService(TagDOMapper tagDOMapper,
                      TagRelationDOMapper tagRelationDOMapper) {
        this.tagDOMapper = tagDOMapper;
        this.tagRelationDOMapper = tagRelationDOMapper;
    }

    @Cacheable
    public List<TagDO> getAllTag(){
        TagDOExample example = new TagDOExample();
        example.createCriteria();
        return tagDOMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable
    public List<TagRelationDO> getTagRelationByTargetId(Long targetId) {
        TagRelationDOExample tagRelationDOExample = new TagRelationDOExample();
        tagRelationDOExample.createCriteria().andTargetIdEqualTo(targetId);
        return tagRelationDOMapper.selectByExample(tagRelationDOExample);
    }

    @Cacheable
    public List<TagRelationDO> getTagRelationByTagIds(List<Long> tagIds) {
        if (tagIds != null && tagIds.size() > 0) {
            TagRelationDOExample tagRelationDOExample = new TagRelationDOExample();
            tagRelationDOExample.createCriteria().andTagIdIn(tagIds);
            return tagRelationDOMapper.selectByExample(tagRelationDOExample);
        } else {
            return null;
        }
    }

    @Cacheable
    public List<TagDO> getTagByEnName(String enName) {
        TagDOExample tagDOExample = new TagDOExample();
        tagDOExample.createCriteria().andEnNameEqualTo(enName);
        return tagDOMapper.selectByExampleWithBLOBs(tagDOExample);
    }

    @Cacheable
    public List<TagRelationDO> getTagRelationByEnName(String enName, Long typeId) {
        TagDOExample tagDOExample = new TagDOExample();
        tagDOExample.createCriteria().andEnNameEqualTo(enName);
        List<TagDO> tagDOS = tagDOMapper.selectByExampleWithBLOBs(tagDOExample);
        if (tagDOS != null && tagDOS.size() > 0) {
            TagRelationDOExample tagRelationDOExample = new TagRelationDOExample();
            tagRelationDOExample.createCriteria()
                    .andTagIdEqualTo(tagDOS.get(0).getId())
                    .andTypeIdEqualTo(typeId);
            return tagRelationDOMapper.selectByExample(tagRelationDOExample);
        } else {
            return null;
        }
    }
}
