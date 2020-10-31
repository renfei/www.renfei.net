package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.entity.CategoryDTO;
import net.renfei.repository.CategoryDOMapper;
import net.renfei.repository.entity.CategoryDO;
import net.renfei.repository.entity.CategoryDOExample;
import net.renfei.repository.entity.TypeDO;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: CategoryService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "CategoryService")
public class CategoryService extends BaseService {
    private final TypeService typeService;
    private final CategoryDOMapper categoryDOMapper;

    public CategoryService(TypeService typeService,
                           CategoryDOMapper categoryDOMapper) {
        this.typeService = typeService;
        this.categoryDOMapper = categoryDOMapper;
    }

    @Cacheable
    public List<CategoryDO> getAllCategoryByType(Long id) {
        CategoryDOExample example = new CategoryDOExample();
        example.createCriteria().andTypeIdEqualTo(id);
        return categoryDOMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable
    public CategoryDTO getCategoryByEnNaeme(String enName) {
        CategoryDOExample categoryDOExample = new CategoryDOExample();
        categoryDOExample.createCriteria()
                .andEnNameEqualTo(enName);
        CategoryDO categoryDO = ListUtils.getOne(categoryDOMapper.selectByExampleWithBLOBs(categoryDOExample));
        if (categoryDO != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(categoryDO, categoryDTO);
            TypeDO typeDO = typeService.getTypeByID(categoryDTO.getTypeId());
            categoryDTO.setTypeName(typeDO.getTypeName());
            categoryDTO.setUriPath(typeDO.getUriPath());
            return categoryDTO;
        } else {
            return null;
        }

    }

    public List<CategoryDTO> getAllCategory() {
        CategoryDOExample categoryDOExample = new CategoryDOExample();
        categoryDOExample.createCriteria();
        List<CategoryDO> categoryDOS = categoryDOMapper.selectByExampleWithBLOBs(categoryDOExample);
        if (categoryDOS != null && categoryDOS.size() > 0) {
            List<CategoryDTO> categoryDTOS = new ArrayList<>();
            for (CategoryDO cdo : categoryDOS
            ) {
                CategoryDTO categoryDTO = new CategoryDTO();
                BeanUtils.copyProperties(cdo, categoryDTO);
                TypeDO typeDO = typeService.getTypeByID(categoryDTO.getTypeId());
                if (typeDO != null) {
                    categoryDTO.setTypeName(typeDO.getTypeName());
                    categoryDTO.setUriPath(typeDO.getUriPath());
                }
                categoryDTOS.add(categoryDTO);
            }
            return categoryDTOS;
        } else {
            return null;
        }
    }
}
