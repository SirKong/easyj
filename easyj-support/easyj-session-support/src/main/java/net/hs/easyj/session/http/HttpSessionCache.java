package net.hs.easyj.session.http;

import net.hs.easyj.cache.KeyWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;

import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * HttpSession 缓存工具类
 *
 * @author Gavin Hu
 * @create 2012/4/26
 */
public class HttpSessionCache {

    private final Log log = LogFactory.getLog(HttpSessionCache.class);
	
	private Cache sessionCache;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public HttpSessionCache(Cache sessionCache) {
        this.sessionCache = sessionCache;
    }

    public Object getAttribute(final String sessionId, final String name) {
		Future future = executorService.submit(new Callable(){
			public Object call() throws Exception {
				return sessionCache.get(sessionKey(sessionId, name)).get();
			}
		});
		//
		try {
			return future.get(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Get attribute from session cache error!", e);
		}
		return null;
	}
	
	public void setAttribute(final String sessionId, final String name, final Object value) {
		executorService.execute(new Runnable(){
			public void run() {
                KeyWrapper keyWrapper = KeyWrapper.wrap(sessionKey(sessionId, name), 0);
                sessionCache.put(keyWrapper, value);
			}
		});
	}
	
	public void removeAttribute(final String sessionId, final String name) {
		executorService.execute(new Runnable(){
			public void run() {
                //
				sessionCache.evict(sessionKey(sessionId, name));
			}
		});
	}
	
	public void removeAttributes(final String sessionId, final Enumeration attributeNames) {
		executorService.execute(new Runnable(){
			public void run() {
				while(attributeNames.hasMoreElements()) {
					String name = (String)attributeNames.nextElement();
                    sessionCache.evict(sessionKey(sessionId, name));
				}
			}
		});
	}
	
	private String sessionKey(String sessionId, String name) {
		return sessionId + "T" + name;
	}
	
}
