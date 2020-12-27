package net.renfei.controller;

import net.renfei.base.BaseController;
import net.renfei.config.RenFeiConfig;
import net.renfei.discuz.ucenter.api.UCClient;
import net.renfei.discuz.ucenter.client.Client;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: UCenterController</p>
 * <p>Description: UCenter接口</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@ApiIgnore
@Controller
@RequestMapping("/ucenter")
public class UCenterController extends BaseController {
    protected UCenterController(RenFeiConfig renFeiConfig, GlobalService globalService, CommentsService commentsService, PaginationService paginationService) {
        super(renFeiConfig, globalService, commentsService, paginationService);
    }

    @ResponseBody
    @RequestMapping("api/uc.php")
    public void uc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UCClient ucClient = new UCClient();
        Client client =
                new Client(renFeiConfig.getUCenter().getApi(),
                        null,
                        renFeiConfig.getUCenter().getKey(),
                        renFeiConfig.getUCenter().getAppId(),
                        renFeiConfig.getUCenter().getConnect());
        String result = ucClient.doAnswer(client, request, response);
        response.getWriter().print(result);
    }
}
