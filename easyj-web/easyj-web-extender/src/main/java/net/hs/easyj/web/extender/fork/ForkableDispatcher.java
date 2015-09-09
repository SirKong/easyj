package net.hs.easyj.web.extender.fork;

import net.hs.easyj.web.extender.dispatch.Dispatcher;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 可拆分的分发器
 *
 * @author Gavin Hu
 * @create 2015/2/7
 */
public class ForkableDispatcher {

    public static final String FORKED_REQUEST_AND_RESPONSES = "forkedMap";

    private Forkable forkable;

    private Dispatcher dispatcher;

    private ExecutorService executorService;

    public ForkableDispatcher(Dispatcher dispatcher, ServletContext context) {
        this.dispatcher = dispatcher;
        this.forkable = newForkable(context);
        this.executorService = newExecutorService(context);
    }

    private Forkable newForkable(ServletContext context) {
        String forkableClassName = context.getInitParameter("forkableClass");
        try {
            Class<?> forkableClass = getClass().getClassLoader().loadClass(forkableClassName);
            Forkable forkable = (Forkable) forkableClass.newInstance();
            forkable.setServletContext(context);
            return forkable;
        } catch (ClassNotFoundException e) {
            context.log(e.getMessage());
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private ExecutorService newExecutorService(ServletContext context) {
        int forkPoolSize = 0;
        String forkPoolSizeStr = context.getInitParameter("forkPoolSize");
        if(forkPoolSizeStr!=null) {
            forkPoolSize = Integer.parseInt(forkPoolSizeStr);
        }
        //
        return forkPoolSize>0 ? Executors.newFixedThreadPool(forkPoolSize) : Executors.newCachedThreadPool();
    }

    public boolean canFork(final HttpServletRequest req, final HttpServletResponse res) {
        //
        return forkable!=null && forkable.canFork(req);
    }

    public void forkAndDispatch(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        //
        List<ForkedRequestAndResponse> forkedRequestAndResponses = forkable.fork(req, res);
        //
        List<Future<ForkedRequestAndResponse>> futures = new ArrayList<Future<ForkedRequestAndResponse>>();
        for(final ForkedRequestAndResponse forkedRequestAndResponse : forkedRequestAndResponses) {
            //
            Future<ForkedRequestAndResponse> future = executorService.submit(new Callable<ForkedRequestAndResponse>() {
                @Override
                public ForkedRequestAndResponse call() throws Exception {
                    //
                    dispatcher.dispatch(forkedRequestAndResponse.getForkedRequest(), forkedRequestAndResponse.getForkedResponse());
                    //
                    return forkedRequestAndResponse;
                }
            });
            //
            futures.add(future);
        }
        //
        Map<String, ForkedRequestAndResponse> forkedMap = new LinkedHashMap<String, ForkedRequestAndResponse>();
        for(Future<ForkedRequestAndResponse> future : futures) {
            //
            try {
                ForkedRequestAndResponse forkedRequestAndResponse = future.get();
                //
                forkedMap.put(forkedRequestAndResponse.getForkedName(), forkedRequestAndResponse);
                //
            } catch (Exception e) {
                throw new ServletException(e.getMessage(), e);
            }
        }
        //
        req.setAttribute(FORKED_REQUEST_AND_RESPONSES, forkedMap);
        //
        dispatcher.dispatch(req, res);
    }

}
