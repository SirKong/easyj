package net.hs.easyj.web.extender.fork;

/**
 * 拆分的请求 和 响应
 *
 * @author Gavin Hu
 * @create 2015/2/7
 */
public class ForkedRequestAndResponse {

    private String forkedName;

    private ForkedHttpServletRequest forkedRequest;

    private ForkedHttpServletResponse forkedResponse;

    public ForkedRequestAndResponse(String forkedName, ForkedHttpServletRequest forkedRequest, ForkedHttpServletResponse forkedResponse) {
        this.forkedName = forkedName;
        this.forkedRequest = forkedRequest;
        this.forkedResponse = forkedResponse;
    }

    public String getForkedName() {
        return forkedName;
    }

    public ForkedHttpServletRequest getForkedRequest() {
        return forkedRequest;
    }

    public ForkedHttpServletResponse getForkedResponse() {
        return forkedResponse;
    }
}
