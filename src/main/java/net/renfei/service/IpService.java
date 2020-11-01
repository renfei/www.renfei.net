package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.IpInfoDTO;
import net.renfei.exception.BusinessException;
import net.renfei.ip2location.IP2Location;
import net.renfei.ip2location.IPResult;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.Builder;
import net.renfei.sdk.utils.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * <p>Title: IpService</p>
 * <p>Description: IP服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "IpService")
public class IpService extends BaseService {
    private static final String OK = "OK";
    private static final String EMPTY_IP_ADDRESS = "EMPTY_IP_ADDRESS";
    private static final String INVALID_IP_ADDRESS = "INVALID_IP_ADDRESS";
    private static final String MISSING_FILE = "MISSING_FILE";
    private static final String IPV6_NOT_SUPPORTED = "IPV6_NOT_SUPPORTED";
    private static final Long IPV4_BIG_INTEGER = 4294967295L;
    private IP2Location ip2LocationV4;
    private IP2Location ip2LocationV6;
    protected final RenFeiConfig renFeiConfig;

    public IpService(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
        if (!BeanUtils.isEmpty(renFeiConfig.getIpv4DataPath())) {
            this.ip2LocationV4 = new IP2Location();
            this.ip2LocationV4.IPDatabasePath = renFeiConfig.getIpv4DataPath();
        }
        if (!BeanUtils.isEmpty(renFeiConfig.getIpv6DataPath())) {
            this.ip2LocationV6 = new IP2Location();
            this.ip2LocationV6.IPDatabasePath = renFeiConfig.getIpv6DataPath();
        }
    }

    @Cacheable
    public IpInfoDTO query(String ip) throws BusinessException {
        if (BeanUtils.isEmpty(ip)) {
            throw new BusinessException("IP地址不能为空(IP address cannot be null.)");
        }
        IPResult rec;
        try {
            BigInteger bIp = StringUtils.stringToBigInt(ip);
            if (BigInteger.valueOf(IPV4_BIG_INTEGER).compareTo(bIp) > 0) {
                // IPv4
                if (this.ip2LocationV4 == null) {
                    throw new BusinessException("不支持IPv4地址查询(IPv4 is not supported.)");
                }
                rec = this.ip2LocationV4.IPQuery(ip);
            } else if (this.ip2LocationV6 != null) {
                // IPv6
                rec = this.ip2LocationV6.IPQuery(ip);
            } else {
                throw new BusinessException("不支持IPv6查询(IPv6 is not supported.)");
            }
            if (OK.equals(rec.getStatus())) {
                return convert(rec);
            } else if (EMPTY_IP_ADDRESS.equals(rec.getStatus())) {
                throw new BusinessException("IP地址不能为空(IP address cannot be blank.)");
            } else if (INVALID_IP_ADDRESS.equals(rec.getStatus())) {
                throw new BusinessException("无效的IP地址(Invalid IP address.)");
            } else if (MISSING_FILE.equals(rec.getStatus())) {
                throw new BusinessException("无效的数据库地址(Invalid database path.)");
            } else if (IPV6_NOT_SUPPORTED.equals(rec.getStatus())) {
                throw new BusinessException("不包含IPv6数据(This BIN does not contain IPv6 data.)");
            } else {
                throw new BusinessException("未知错误(Unknown error.)::" + rec.getStatus());
            }
        } catch (NumberFormatException numberFormatException) {
            throw new BusinessException("无效的IP地址(Invalid IP address.)");
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            throw new BusinessException("无效的IP地址(Invalid IP address.)");
        } catch (IOException ioException) {
            log.error(ioException.getMessage(), ioException);
            throw new BusinessException("IOException.");
        }
    }

    private IpInfoDTO convert(IPResult ipResult) {
        return Builder.of(IpInfoDTO::new)
                .with(IpInfoDTO::setIpAddress, ipResult.getIp_address())
                .with(IpInfoDTO::setCountryShort, ipResult.getCountry_short())
                .with(IpInfoDTO::setCountryLong, ipResult.getCountry_long())
                .with(IpInfoDTO::setRegion, ipResult.getRegion())
                .with(IpInfoDTO::setCity, ipResult.getCity())
                .with(IpInfoDTO::setIsp, ipResult.getISP())
                .with(IpInfoDTO::setLatitude, ipResult.getLatitude())
                .with(IpInfoDTO::setLongitude, ipResult.getLongitude())
                .with(IpInfoDTO::setDomain, ipResult.getDomain())
                .with(IpInfoDTO::setZipcode, ipResult.getZipCode())
                .with(IpInfoDTO::setNetspeed, ipResult.getNetSpeed())
                .with(IpInfoDTO::setTimezone, ipResult.getTimeZone())
                .with(IpInfoDTO::setIddcode, ipResult.getIDDCode())
                .with(IpInfoDTO::setAreacode, ipResult.getAreaCode())
                .with(IpInfoDTO::setWeatherstationcode, ipResult.getWeatherStationCode())
                .with(IpInfoDTO::setWeatherstationname, ipResult.getWeatherStationName())
                .with(IpInfoDTO::setMcc, ipResult.getMCC())
                .with(IpInfoDTO::setMnc, ipResult.getMNC())
                .with(IpInfoDTO::setMobilebrand, ipResult.getMobileBrand())
                .with(IpInfoDTO::setElevation, ipResult.getElevation())
                .with(IpInfoDTO::setUsagetype, ipResult.getUsageType())
                .with(IpInfoDTO::setStatus, ipResult.getStatus())
                .build();
    }
}
