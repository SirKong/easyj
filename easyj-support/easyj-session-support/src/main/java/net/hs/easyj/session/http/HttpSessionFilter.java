package net.hs.easyj.session.http;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * Session 过滤器
 *
 * @author Gavin Hu
 * @create 2012/4/26
 */
public class HttpSessionFilter extends OncePerRequestFilter {

    private boolean enabled = true;

    private String cookieName = "JSESSIONID";

    private HttpSessionCache sessionCache;

    private String sessionCacheName = "httpSessionCache";

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        //
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        //
        Cache cache = webApplicationContext.getBean(sessionCacheName, Cache.class);
        this.sessionCache = new HttpSessionCache(cache);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//
		if(enabled) {
			request = processSharedSession(request, response);
		}
		//
		filterChain.doFilter(request, response);
	}
	
	protected HttpServletRequest processSharedSession(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		//
		String _sessionId = request.getSession().getId();
		if(cookie==null) {
			if(StringUtils.contains(_sessionId, "!")) {
				_sessionId = StringUtils.substringBefore(_sessionId, "!");
			}
			//
			cookie = new Cookie(cookieName, _sessionId);
			cookie.setPath("/");
			cookie.setComment("The shared session id");
		} else {
			_sessionId = cookie.getValue();
		}
		// 设置 cookie 有效时间
		cookie.setMaxAge(request.getSession().getMaxInactiveInterval());
		response.addCookie(cookie);
		//
        final String sessionId = _sessionId;
        //
        return new HttpServletRequestWrapper(request) {

            public HttpSession getSession() {
                return new HttpSessionProxy(super.getSession(), sessionId, sessionCache);
            }

            public HttpSession getSession(boolean create) {
                return new HttpSessionProxy(super.getSession(create), sessionId, sessionCache);
            }

        };
	}

}
