package net.hs.easyj.web.component.mvc;

import com.alibaba.fastjson.JSONObject;
import net.hs.easyj.web.component.loader.UIComponentDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UI 组件处理器
 *
 * @author Gavin Hu
 * @create 2015/5/25
 */
public class UIComponentHandler implements HttpRequestHandler {

    private UIComponentDataLoader uiComponentDataLoader;

    @Autowired
    public void setUiComponentDataLoader(UIComponentDataLoader uiComponentDataLoader) {
        this.uiComponentDataLoader = uiComponentDataLoader;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        if(uiComponentDataLoader.support(request)) {
            Object data = uiComponentDataLoader.loadData(request);
            //
            byte[] jsonBytes = JSONObject.toJSONBytes(data);
            FileCopyUtils.copy(jsonBytes, response.getOutputStream());
        }
    }

}
