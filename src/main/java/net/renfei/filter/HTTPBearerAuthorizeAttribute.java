package net.renfei.filter;

import io.jsonwebtoken.Claims;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.AccountDTO;
import net.renfei.exceptions.ServiceException;
import net.renfei.sdk.comm.StateCode;
import net.renfei.service.AccountService;
import net.renfei.util.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>Title: HTTPBearerAuthorizeAttribute</p>
 * <p>Description: JWT 校验 filter</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@WebFilter(filterName = "jwtFilter", urlPatterns = "/api/*")
public class HTTPBearerAuthorizeAttribute implements Filter {
    private final JwtUtils jwtUtils;
    private final RenFeiConfig renFeiConfig;
    private final AccountService accountService;

    public HTTPBearerAuthorizeAttribute(JwtUtils jwtUtils,
                                        RenFeiConfig renFeiConfig,
                                        AccountService accountService) {
        this.jwtUtils = jwtUtils;
        this.renFeiConfig = renFeiConfig;
        this.accountService = accountService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (!"SESSION".equals(renFeiConfig.getAuthMode())) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            if ("options".equals(httpRequest.getMethod())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            String auth = httpRequest.getHeader("Authorization");
            if (auth != null && auth.length() > 7) {
                String headStr = auth.substring(0, 6).toLowerCase();
                if (headStr.compareTo("Bearer") == 0) {
                    auth = auth.substring(7);
                    Claims claims = jwtUtils.parseJWT(auth);
                    if (claims != null) {
                        // 查询用户信息
                        AccountDTO accountDTO = accountService.getAccountDTOByUuid(claims.getSubject());
                        String[] authorities = accountDTO.getAuthorities().toArray(new String[0]);
                        //将用户信息和权限填充 到用户身份token对象中
                        UsernamePasswordAuthenticationToken authenticationToken
                                = new UsernamePasswordAuthenticationToken(accountDTO, null, AuthorityUtils.createAuthorityList(authorities));
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                        //将authenticationToken填充到安全上下文
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
            // token 校验失败，抛出异常
            throw new ServiceException(StateCode.Unauthorized, "身份校验失败，请重新登录");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
