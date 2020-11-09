package net.renfei.controller.api;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.SystemLog;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.exception.BusinessException;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.IpUtils;
import net.renfei.sdk.utils.NumberUtils;
import net.renfei.service.*;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: APIController</p>
 * <p>Description: API</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@RestController
@Api(value = "开放接口", tags = "开放接口")
@RequestMapping("/api")
public class APIController extends BaseController {
    private final IpService ipService;
    private final SearchService searchService;
    private final DomainNameService domainNameService;

    protected APIController(GlobalService globalService,
                            IpService ipService,
                            RenFeiConfig renFeiConfig,
                            CommentsService commentsService,
                            PaginationService paginationService,
                            SearchService searchService, DomainNameService domainNameService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
        this.ipService = ipService;
        this.searchService = searchService;
        this.domainNameService = domainNameService;
    }

    @GetMapping("ip/{ip}")
    @ApiOperation(value = "查询IP地址信息", notes = "查询IP地址信息", tags = "开放接口")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "查询IP地址信息")
    public APIResult<IpInfoDTO> queryIpInfo(@PathVariable("ip") String ip) {
        try {
            return new APIResult<>(ipService.query(ip));
        } catch (BusinessException businessException) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message(businessException.getMessage())
                    .build();
        }
    }

    @GetMapping("ip")
    @ApiOperation(value = "查询我的IP地址信息", notes = "查询我的IP地址信息", tags = "开放接口")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "查询我的IP地址信息")
    public APIResult<IpInfoDTO> queryIpInfo() {
        try {
            return new APIResult<>(ipService.query(IpUtils.getIpAddress(request)));
        } catch (BusinessException businessException) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message(businessException.getMessage())
                    .build();
        }
    }

    @GetMapping("dns/dig/{domain}")
    @ApiOperation(value = "域名 dig+trace", notes = "使用该接口可以查询域名的解析过程", tags = "开放接口")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "域名 dig+trace")
    public APIResult<String> getDomainDigTrace(@PathVariable(value = "domain") String domain) {
        return domainNameService.execDigTrace(domain);
    }

    @GetMapping("dns/whois/{domain}")
    @ApiOperation(value = "域名Whois信息查询工具",
            notes = "域名Whois信息查询工具，查询域名是否已经被注册，以及注册域名的详细信息的数据库（如域名所有人、域名注册商、域名注册日期和过期日期等）。通过域名Whois服务器查询，可以查询域名归属者联系方式，以及注册和到期时间。",
            tags = "开放接口")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "域名Whois信息查询")
    public APIResult<String> getDomainWhois(@PathVariable(value = "domain") String domain) {
        return domainNameService.execWhois(domain);
    }

    @GetMapping("uuid")
    @ApiOperation(value = "UUID/GUID 生成接口", notes = "批量生成 UUID/GUID", tags = "开放接口")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "UUID/GUID 生成")
    public APIResult<List<String>> getUuid(@RequestParam(value = "quantity", required = false) String quantity,
                                           @RequestParam(value = "upperCase", required = false) Boolean upperCase,
                                           @RequestParam(value = "hyphen", required = false) Boolean hyphen) {
        int lQuantity = NumberUtils.parseInt(quantity, 1);
        if (lQuantity <= 0) {
            lQuantity = 1;
        }
        if (lQuantity > 1000) {
            lQuantity = 1000;
        }
        if (upperCase == null) {
            upperCase = true;
        }
        if (hyphen == null) {
            hyphen = true;
        }
        List<String> stringUuid = new ArrayList<>();
        for (; lQuantity > 0; lQuantity--) {
            String uuid = UUID.randomUUID().toString();
            if (upperCase) {
                uuid = uuid.toUpperCase();
            } else {
                uuid = uuid.toLowerCase();
            }
            if (!hyphen) {
                uuid = uuid.replace("-", "");
            }
            stringUuid.add(uuid);
        }
        return new APIResult(stringUuid);
    }

    @PostMapping("freemarker/test")
    @ApiOperation(value = "FreeMarker(FTL)在线测试工具", notes = "FreeMarker(FTL)在线测试工具", tags = "开放接口")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "FreeMarker(FTL)在线测试")
    public APIResult<String> getContentByFreeMarkerAndBean(String ftl, String beanJson) {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        freemarker.cache.StringTemplateLoader templateLoader = new freemarker.cache.StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
        APIResult<String> apiResult;
        try {
            freemarker.template.Template template = new freemarker.template.Template("freemarkerTest", ftl, configuration);
            StringWriter stringWriter = new StringWriter();
            Object object = JSON.parseObject(beanJson, Object.class);
            template.process(object, stringWriter);
            apiResult = APIResult.builder()
                    .code(StateCode.OK)
                    .message("")
                    .data(stringWriter.toString())
                    .build();
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            apiResult = APIResult.builder()
                    .code(StateCode.Error)
                    .message(ex.getMessage())
                    .data(ex.getMessage())
                    .build();
        }
        return apiResult;
    }

    @PostMapping("wordIkAnalyze")
    @ApiOperation(value = "中文分词工具API", notes = "中文分词工具API", tags = "开放接口")
    @SystemLog(logLevel = LogLevel.INFO, logModule = LogModule.OPENAPI, logType = LogType.GET, logDesc = "IK分词工具API")
    public APIResult<List<IkAnalyzeVO>> getWordIkAnalyze(@RequestBody String word) {
        if (BeanUtils.isEmpty(word)) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("待分词内容为空，请检查入参。")
                    .build();
        }
        try {
            return new APIResult<>(searchService.getIkAnalyzeTerms(word));
        } catch (Exception exception) {
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message("中文分词服务暂时不可用，请稍后再试。")
                    .build();
        }

    }
}
