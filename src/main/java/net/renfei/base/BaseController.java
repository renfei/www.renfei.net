package net.renfei.base;

import eu.bitwalker.useragentutils.*;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.*;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.CommentsService;
import net.renfei.service.GlobalService;
import net.renfei.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static eu.bitwalker.useragentutils.DeviceType.COMPUTER;

/**
 * <p>Title: BaseController</p>
 * <p>Description: 控制层基础类</p>
 *
 * @author RenFei
 */
public abstract class BaseController {
    protected static final String HEAD_KEY = "headVO";
    protected static final String HEADER_KEY = "headerVO";
    protected static final String FOOTER_KEY = "footerVO";
    protected static final String ACTIVE_KEY = "active";
    protected static final String SESSION_KEY = "accountSession";
    protected final RenFeiConfig renFeiConfig;
    protected final GlobalService globalService;
    protected final CommentsService commentsService;
    protected final PaginationService paginationService;
    @Autowired
    protected HttpServletRequest request;

    protected BaseController(RenFeiConfig renFeiConfig,
                             GlobalService globalService,
                             CommentsService commentsService,
                             PaginationService paginationService) {
        this.renFeiConfig = renFeiConfig;
        this.globalService = globalService;
        this.commentsService = commentsService;
        this.paginationService = paginationService;
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param mv ModelAndView
     */
    @ModelAttribute
    public void modelAttribute(ModelAndView mv) {
        mv.addObject(HEAD_KEY, globalService.getGlobalHead());
        HeaderVO headerVO = globalService.getGlobalHeader();
        headerVO.setAccount(getUser());
        mv.addObject(HEADER_KEY, headerVO);
        FooterVO footerVO = globalService.getGlobalFooter();
        if (!"prod".equals(renFeiConfig.getActive())) {
            // 非生产环境，移除谷歌广告
            List<String> jss = footerVO.getJss();
            IntStream.range(0, jss.size()).filter(i ->
                    jss.get(i).contains("adsbygoogle.js")).
                    boxed().findFirst().map(i -> jss.remove((int) i));

        }
        // 谷歌统计传递用户ID
        if (getUser() != null) {
            footerVO.setJsText(
                    footerVO.getJsText()
                            + "window.dataLayer = window.dataLayer || [];\n"
                            + "function gtag(){dataLayer.push(arguments);}\n"
                            + "gtag('js', new Date());\n"
                            + "gtag('config', '" + renFeiConfig.getGoogle().getAnalytics() + "', {\n"
                            + "    'user_id': '" + getUser().getUserName() + "'\n"
                            + "});\n"
            );
        } else {
            footerVO.setJsText(
                    footerVO.getJsText()
                            + "window.dataLayer = window.dataLayer || [];\n"
                            + "function gtag(){dataLayer.push(arguments);}\n"
                            + "gtag('js', new Date());\n"
                            + "gtag('config', '" + renFeiConfig.getGoogle().getAnalytics() + "');\n"
            );
        }
        mv.addObject(FOOTER_KEY, footerVO);
        mv.addObject(ACTIVE_KEY, renFeiConfig.getActive());
        mv.addObject("account", getUser());
    }

    protected void setHead(ModelAndView mv, String description) {
        setHead(mv, description, null);
    }

    protected void setHead(ModelAndView mv, String description, String keywords) {
        setHead(mv, description, keywords, null);
    }

    protected void setHead(ModelAndView mv, String description, String keywords, OGprotocol opg) {
        HeadVO pageHeadVO = getHead(mv);
        pageHeadVO.setDescription(description);
        pageHeadVO.setKeywords(keywords);
        pageHeadVO.setOgprotocol(opg);
        mv.addObject(HEAD_KEY, pageHeadVO);
    }

    protected void throwNoHandlerFoundException() throws NoHandlerFoundException {
        HttpHeaders headers = new HttpHeaders();
        throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), headers);
    }

    protected void setPagination(ModelAndView mv, String page, Long count, String link) {
        setPagination(mv, page, count, 10, link);
    }

    protected void setPagination(ModelAndView mv, String page, Long count, int rows, String link) {
        int totalPage = Integer.parseInt((count / rows) + "");
        if (count % rows > 0) {
            totalPage++;
        }
        if (totalPage <= 0) {
            totalPage = 1;
        }
        mv.addObject("paginationList", paginationService.getPagination(page, totalPage, renFeiConfig.getDomain() + link));
    }

    protected void setComment(ModelAndView mv, Long typeid, Long id) {
        List<CommentVO> commentVOS = new ArrayList<>();
        List<CommentDTO> commentDTOS = commentsService.getComment(typeid, id);
        if (commentDTOS != null) {
            for (CommentDTO dto : commentDTOS
            ) {
                CommentVO commentVO = new CommentVO();
                commentVO = commentVO.convert(dto);
                commentVOS.add(commentVO);
            }
        }
        mv.addObject("commentsTypeId", typeid);
        mv.addObject("commentsObjId", id);
        mv.addObject("commentsVO", commentVOS);
    }

    protected AccountDTO getUser() {
        Object session = request.getSession().getAttribute(SESSION_KEY);
        if (session instanceof AccountDTO) {
            return (AccountDTO) session;
        }
        return null;
    }

    protected void updateUser(AccountDTO accountDTO) {
        request.getSession().setAttribute(SESSION_KEY, accountDTO);
    }

    protected ModelAndView checkSigned() {
        if (getUser() == null) {
            return new ModelAndView("redirect:/auth/signIn?callback=" + String.valueOf(request.getRequestURL()));
        }
        return null;
    }

    protected String getCallBack(String callback) {
        if (!BeanUtils.isEmpty(callback)) {
            try {
                URL url = new URL(callback);
                String host = url.getHost();
                if (host.endsWith(".renfei.net")) {
                    return callback;
                }
            } catch (MalformedURLException ignored) {
                return "";
            }
        }
        return "";
    }

    private HeadVO getHead(ModelAndView mv) {
        Map<String, Object> map = mv.getModel();
        Object obj = map.get(HEAD_KEY);
        if (obj instanceof HeadVO) {
            return (HeadVO) obj;
        }
        return null;
    }
}
