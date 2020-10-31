package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.TypeDOMapper;
import net.renfei.repository.entity.TypeDO;
import net.renfei.repository.entity.TypeDOExample;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "TypeService")
public class TypeService extends BaseService {
    private final TypeDOMapper typeDOMapper;

    public TypeService(TypeDOMapper typeDOMapper) {
        this.typeDOMapper = typeDOMapper;
    }

    @Cacheable
    public TypeDO getTypeByID(Long id) {
        return typeDOMapper.selectByPrimaryKey(id);
    }

    @Cacheable
    public List<TypeDO> getAllType() {
        TypeDOExample typeDOExample = new TypeDOExample();
        typeDOExample.createCriteria();
        return typeDOMapper.selectByExample(typeDOExample);
    }

    @Cacheable
    public List<TypeDO> getTypeByName(String name) {
        TypeDOExample typeDOExample = new TypeDOExample();
        typeDOExample.createCriteria().andTypeNameEqualTo(name);
        return typeDOMapper.selectByExample(typeDOExample);
    }
}
