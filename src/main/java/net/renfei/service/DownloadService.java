package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.DownloadDOMapper;
import net.renfei.repository.entity.DownloadDO;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "DownloadService")
public class DownloadService extends BaseService {
    private final DownloadDOMapper downloadDOMapper;

    public DownloadService(DownloadDOMapper downloadDOMapper) {
        this.downloadDOMapper = downloadDOMapper;
    }

    @Cacheable
    public DownloadDO getDownloadInfoById(String id) {
        Long ID = 0L;
        if (!BeanUtils.isEmpty(id)) {
            try {
                ID = Long.valueOf(id);
                return downloadDOMapper.selectByPrimaryKey(ID);
            } catch (NumberFormatException nfe) {
                return null;
            }
        } else {
            return null;
        }
    }
}
