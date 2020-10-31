package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.CacheDOMapper;
import net.renfei.repository.entity.CacheDO;
import net.renfei.repository.entity.CacheDOExample;
import net.renfei.sdk.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CacheService extends BaseService {
    private final CacheDOMapper cacheDOMapper;

    public CacheService(CacheDOMapper cacheDOMapper) {
        this.cacheDOMapper = cacheDOMapper;
    }

    public String get(String key) {
        if (key != null && key.length() > 0) {
            //此处可以扩展去Redis中查询
            return getFromDB(key);
        } else {
            return null;
        }
    }

    public void set(String key, String value, Date expires) {
        if (key != null && key.length() > 0) {
            setFromDB(key, value, expires);
        }
    }

    public String getRandomKey() {
        String key = "";
        //默认生成4位数的Key
        int length = 4;
        for (int i = 0; ; i++) {
            key = StringUtils.getRandomString(length);
            if (get(key) == null) {
                return key;
            } else if (i > 10) {
                //10次循环还是没找到，Key的位数应该加一位
                length++;
                i = 0;
            }
        }
    }

    private String getFromDB(String key) {
        if (key != null && key.length() > 0) {
            CacheDOExample cacheDOExample = new CacheDOExample();
            cacheDOExample.createCriteria()
                    .andExpiresGreaterThan(new Date())
                    .andCacheKeyEqualTo(key);
            List<CacheDO> cacheDOList = cacheDOMapper.selectByExampleWithBLOBs(cacheDOExample);
            if (cacheDOList != null && cacheDOList.size() > 0) {
                return cacheDOList.get(0).getCacheValue();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private CacheDO getCacheDOFromDB(String key) {
        if (key != null && key.length() > 0) {
            CacheDOExample cacheDOExample = new CacheDOExample();
            cacheDOExample.createCriteria()
                    .andExpiresGreaterThan(new Date())
                    .andCacheKeyEqualTo(key);
            List<CacheDO> cacheDOList = cacheDOMapper.selectByExampleWithBLOBs(cacheDOExample);
            if (cacheDOList != null && cacheDOList.size() > 0) {
                return cacheDOList.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private void setFromDB(String key, String value, Date expires) {
        if (key != null && key.length() > 0) {
            //先查询当前有没有存在的
            CacheDO cacheDO = getCacheDOFromDB(key);
            if (cacheDO != null) {
                //存在的话进行更新
                if (value != null && value.length() > 0 && expires != null) {
                    cacheDO.setCacheValue(value);
                    cacheDO.setExpires(expires);
                } else {
                    //立即过期
                    cacheDO.setExpires(new Date(System.currentTimeMillis() - 10000));
                }
                cacheDOMapper.updateByPrimaryKeySelective(cacheDO);
            } else {
                //不存在就插入
                if (value != null && value.length() > 0 && expires != null) {
                    if (new Date().before(expires)) {
                        cacheDO = new CacheDO();
                        cacheDO.setUuid(UUID.randomUUID().toString());
                        cacheDO.setCacheValue(value);
                        cacheDO.setCacheKey(key);
                        cacheDO.setExpires(expires);
                        cacheDOMapper.insertSelective(cacheDO);
                    }
                }
            }
        }
    }
}
