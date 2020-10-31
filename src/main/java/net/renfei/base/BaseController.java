package net.renfei.base;

import net.renfei.config.RenFeiConfig;
import net.renfei.entity.CommentDTO;
import net.renfei.entity.CommentVO;
import net.renfei.entity.HeadVO;
import net.renfei.entity.OGprotocol;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        mv.addObject(HEADER_KEY, globalService.getGlobalHeader());
        mv.addObject(FOOTER_KEY, globalService.getGlobalFooter());
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

    private HeadVO getHead(ModelAndView mv) {
        Map<String, Object> map = mv.getModel();
        Object obj = map.get(HEAD_KEY);
        if (obj instanceof HeadVO) {
            return (HeadVO) obj;
        }
        return null;
    }
}
