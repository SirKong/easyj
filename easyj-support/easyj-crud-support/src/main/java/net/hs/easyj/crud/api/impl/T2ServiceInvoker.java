package net.hs.easyj.crud.api.impl;

import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.event.EventReturnCode;
import com.hundsun.t2sdk.interfaces.share.event.EventTagdef;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * t2服务调用工具
 *
 * @author wukq14280@hundsun.com,created at 2015年5月28日T上午11:27:44
 */
public class T2ServiceInvoker {
    private T2Services t2Services = T2Services.getInstance();
    private static boolean initialized;
    private String clientName = "default";

    /**
     * 调用t2服务
     *
     * @param serviceIdentity
     * @param request
     * @return
     * @throws T2SDKException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public IDataset invoke(String serviceIdentity, Map<String, Object> request) {
        IDataset dataset = null;
        try {
            Map<String, Object> head = new HashMap<String, Object>();
            head.put(EventTagdef.TAG_FUNCTION_ID, serviceIdentity);
            //
            IEvent event;
            event = getClient().sendReceive(head, (Map) request);

            if (event.getReturnCode() == EventReturnCode.I_OK) {
                dataset = event.getEventDatas().getDataset(0);
            }
        } catch (T2SDKException e) {
            throw new RuntimeException(e);
        }
        return dataset;
    }

    private IClient getClient() throws T2SDKException {
        if (!initialized) {
            synchronized (this) {
                if (!initialized) {
                    t2Services.init();
                    t2Services.start();
                    //
                    Runtime.getRuntime().addShutdownHook(new Thread() {
                        @Override
                        public void run() {
                            t2Services.stop();
                        }
                    });
                    //
                    initialized = true;
                }
            }
        }
        return t2Services.getClient(clientName);
    }
}
