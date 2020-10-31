package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.repository.SettingDOMapper;
import net.renfei.repository.entity.SettingDO;
import net.renfei.repository.entity.SettingDOExample;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: SettingService</p>
 * <p>Description: 设置服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "SettingService")
public class SettingService extends BaseService {
    private final SettingDOMapper settingDOMapper;
    private static final String NOTICE_KEY = "notice";

    public SettingService(SettingDOMapper settingDOMapper) {
        this.settingDOMapper = settingDOMapper;
    }

    @Cacheable
    public String getNotice() {
        SettingDO settingDO = ListUtils.getOne(getSettingByKey(NOTICE_KEY));
        if (settingDO == null) {
            return null;
        }
        return settingDO.getValues();
    }

    @Cacheable
    public List<SettingDO> getSettingByKey(String key) {
        SettingDOExample example = new SettingDOExample();
        example.setOrderByClause("orders ASC");
        example.createCriteria()
                .andKeysEqualTo(key);
        return settingDOMapper.selectByExampleWithBLOBs(example);
    }
}
