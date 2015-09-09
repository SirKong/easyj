package net.hs.easyj.session.http;

import javax.servlet.http.HttpSession;

/**
 * HttpSession 代理
 *
 * @author Gavin Hu
 * @create 2012/4/26
 */
public class HttpSessionProxy extends HttpSessionWrapper {
	
	private String sessionId;

    private HttpSessionCache sessionStore;

	public HttpSessionProxy(HttpSession httpSession, String sessionId, HttpSessionCache sessionStore) {
		super(httpSession);
		//
		this.sessionId = sessionId;
        this.sessionStore = sessionStore;
	}

	public Object getAttribute(String name) {
		Object value = super.getAttribute(name);
		if(value==null) {
			//
			value = sessionStore.getAttribute(sessionId, name);
			//
			if(value!=null) {
				super.setAttribute(name, value);
			}
		}
		return value;
	}
	
	public void removeAttribute(String name) {
		super.removeAttribute(name);
		//
		sessionStore.removeAttribute(sessionId, name);
	}

	public void setAttribute(String name, Object value) {
		super.setAttribute(name, value);
		//
		sessionStore.setAttribute(sessionId, name, value);
	}
	
	public void invalidate() {
		sessionStore.removeAttributes(sessionId, getAttributeNames());
		//
		super.invalidate();
	}
		
}
