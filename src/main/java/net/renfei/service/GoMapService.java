package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.GoMapDOMapper;
import net.renfei.repository.entity.GoMapDO;
import net.renfei.repository.entity.GoMapDOExample;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.stereotype.Service;

/**
 * 跳转服务
 *
 * @author renfei
 */
@Service
public class GoMapService extends BaseService {
    private final GoMapDOMapper goMapMapper;

    public GoMapService(GoMapDOMapper goMapMapper) {
        this.goMapMapper = goMapMapper;
    }

    public String getGoMapUrl(String code) {
        if (BeanUtils.isEmpty(code)) {
            return "/";
        }
        GoMapDOExample example = new GoMapDOExample();
        example.createCriteria()
                .andCodeEqualTo(code)
                .andIsDeleteEqualTo(false);
        GoMapDO goMapDO = ListUtils.getOne(goMapMapper.selectByExample(example));
        if (goMapDO == null) {
            return "/";
        }
        return goMapDO.getToUrl();
    }
}
