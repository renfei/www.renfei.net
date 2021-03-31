package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.repository.HolidayDOMapper;
import net.renfei.repository.entity.HolidayDO;
import net.renfei.repository.entity.HolidayDOExample;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author renfei
 */
@Service
public class HolidayService extends BaseService {
    private final HolidayDOMapper holidayMapper;

    public HolidayService(HolidayDOMapper holidayMapper) {
        this.holidayMapper = holidayMapper;
    }

    public HolidayDO getHoliday() {
        HolidayDOExample example = new HolidayDOExample();
        example.createCriteria()
                .andStartDateLessThanOrEqualTo(new Date())
                .andEndDateGreaterThanOrEqualTo(new Date());
        return ListUtils.getOne(holidayMapper.selectByExample(example));
    }
}
