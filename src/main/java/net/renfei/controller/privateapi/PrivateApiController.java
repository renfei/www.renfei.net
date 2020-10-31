package net.renfei.controller.privateapi;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>Title: PrivateApiController</p>
 * <p>Description: 私有接口</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@ApiIgnore
@RestController
@RequestMapping("/private/api")
public class PrivateApiController extends BaseController {
    protected PrivateApiController(RenFeiConfig renFeiConfig,
                                   GlobalService globalService,
                                   CommentsService commentsService,
                                   PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
    }
}
