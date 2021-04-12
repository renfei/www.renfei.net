package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.AdvertisementDOMapper;
import net.renfei.repository.entity.AdvertisementDO;
import net.renfei.repository.entity.AdvertisementDOExample;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.stereotype.Service;

/**
 * 内置广告服务
 *
 * @author renfei
 */
@Service
public class AdvertisementService extends BaseService {
    private final AdvertisementDOMapper advertisementMapper;

    public AdvertisementService(AdvertisementDOMapper advertisementMapper) {
        this.advertisementMapper = advertisementMapper;
    }

    public AdvertisementDO getAdById(Long id) {
        AdvertisementDOExample example = new AdvertisementDOExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andIsDeleteEqualTo(false);
        return ListUtils.getOne(advertisementMapper.selectByExample(example));
    }
}
