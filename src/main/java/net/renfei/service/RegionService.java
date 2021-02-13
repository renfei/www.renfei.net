package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.entity.RegionVO;
import net.renfei.repository.RegionDOMapper;
import net.renfei.repository.entity.RegionDO;
import net.renfei.repository.entity.RegionDOExample;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: RegionService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
public class RegionService extends BaseService {
    private final RegionDOMapper regionMapper;

    public RegionService(RegionDOMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    public List<RegionVO> getRegion(String regionCode) {
        if (regionCode.length() != 6) {
            regionCode = null;
        }
        RegionDOExample example = new RegionDOExample();
        if (BeanUtils.isEmpty(regionCode)) {
            example.createCriteria().andRegionCodeLike("__0000");
        } else if (regionCode.endsWith("0000")) {
            example.createCriteria().andRegionCodeLike(regionCode.substring(0, 2) + "__00");
        } else if (regionCode.endsWith("00")) {
            example.createCriteria().andRegionCodeLike(regionCode.substring(0, 4) + "__");
        } else {
            example.createCriteria().andRegionCodeEqualTo(regionCode);
        }
        List<RegionDO> regionList = regionMapper.selectByExample(example);
        if (BeanUtils.isEmpty(regionList)) {
            return new ArrayList<>(0);
        }
        List<RegionVO> regionVOS = new ArrayList<>(regionList.size());
        for (RegionDO region : regionList
        ) {
            if (region.getRegionCode().equals(regionCode)) {
                continue;
            }
            RegionVO regionVO = new RegionVO();
            org.springframework.beans.BeanUtils.copyProperties(region, regionVO);
            regionVOS.add(regionVO);
        }
        return regionVOS;
    }
}
