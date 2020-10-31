package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.MenuDOMapper;
import net.renfei.repository.entity.MenuDOExample;
import net.renfei.repository.entity.MenuDOWithBLOBs;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: MenuService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
@CacheConfig(cacheNames = "MenuService")
public class MenuService extends BaseService {
    private final MenuDOMapper menuDOMapper;

    public MenuService(MenuDOMapper menuDOMapper) {
        this.menuDOMapper = menuDOMapper;
    }

    @Cacheable
    public List<MenuDOWithBLOBs> getMenus() {
        MenuDOExample example = new MenuDOExample();
        example.setOrderByClause("order_number ASC");
        example.createCriteria().andIsEnableEqualTo(true)
                .andPidEqualTo(0L)
                .andMenuTypeEqualTo(1);
        return menuDOMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable
    public List<MenuDOWithBLOBs> getMenus(Long pid) {
        MenuDOExample example = new MenuDOExample();
        example.setOrderByClause("order_number ASC");
        example.createCriteria().andIsEnableEqualTo(true)
                .andPidEqualTo(pid)
                .andMenuTypeEqualTo(1);
        return menuDOMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable
    public List<MenuDOWithBLOBs> getFooterMenus() {
        MenuDOExample example = new MenuDOExample();
        example.setOrderByClause("order_number ASC");
        example.createCriteria().andIsEnableEqualTo(true)
                .andPidEqualTo(0L)
                .andMenuTypeEqualTo(2);
        return menuDOMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable
    public List<MenuDOWithBLOBs> getFooterMenus(Long pid) {
        MenuDOExample example = new MenuDOExample();
        example.setOrderByClause("order_number ASC");
        example.createCriteria().andIsEnableEqualTo(true)
                .andPidEqualTo(pid)
                .andMenuTypeEqualTo(2);
        return menuDOMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable
    public List<MenuDOWithBLOBs> getSmallMenu() {
        MenuDOExample example = new MenuDOExample();
        example.setOrderByClause("order_number ASC");
        example.createCriteria().andIsEnableEqualTo(true)
                .andPidEqualTo(0L)
                .andMenuTypeEqualTo(3);
        return menuDOMapper.selectByExampleWithBLOBs(example);
    }
}
