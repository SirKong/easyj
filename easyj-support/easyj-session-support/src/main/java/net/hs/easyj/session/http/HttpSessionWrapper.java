package net.hs.easyj.session.http;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * HttpSession 包装器
 *
 * @author Gavin Hu
 * @create 2012-4-26 - 8:03:20
 */
public class HttpSessionWrapper implements HttpSession {
	
	private HttpSession httpSession;
	
	public HttpSessionWrapper(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	public HttpSession getSession() {
		return httpSession;
	}

	public Object getAttribute(String name) {
		return this.httpSession.getAttribute(name);
	}

	public Enumeration getAttributeNames() {
		return this.httpSession.getAttributeNames();
	}

	public long getCreationTime() {
		return this.httpSession.getCreationTime();
	}

	public String getId() {
		return this.httpSession.getId();
	}

	public long getLastAccessedTime() {
		return this.httpSession.getLastAccessedTime();
	}

	public int getMaxInactiveInterval() {
		return this.httpSession.getMaxInactiveInterval();
	}

	public ServletContext getServletContext() {
		return this.httpSession.getServletContext();
	}

	public HttpSessionContext getSessionContext() {
		return this.httpSession.getSessionContext();
	}

	public Object getValue(String name) {
		return this.httpSession.getValue(name);
	}

	public String[] getValueNames() {
		return this.httpSession.getValueNames();
	}

	public void invalidate() {
		this.httpSession.invalidate();
	}

	public boolean isNew() {
		return this.httpSession.isNew();
	}

	public void putValue(String name, Object value) {
		this.httpSession.putValue(name, value);
	}

	public void removeAttribute(String name) {
		this.httpSession.removeAttribute(name);
	}

	public void removeValue(String name) {
		this.httpSession.removeValue(name);
	}

	public void setAttribute(String name, Object value) {
		this.httpSession.setAttribute(name, value);
	}

	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.httpSession.setMaxInactiveInterval(maxInactiveInterval);
	}

}
