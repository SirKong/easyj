package net.hs.easyj.config.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gavin
 * @create 15/8/12
 */
public class SecurityFilter extends OncePerRequestFilter {

    private String redirectPath = "/index.html";

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    public void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        //
        if(lookupPath.startsWith(redirectPath) == false
                && request.getSession().getAttribute("configBean")==null) {
            response.sendRedirect(request.getContextPath() + redirectPath);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
