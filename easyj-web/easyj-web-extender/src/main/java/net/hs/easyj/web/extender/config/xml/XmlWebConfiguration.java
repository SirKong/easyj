package net.hs.easyj.web.extender.config.xml;

import net.hs.easyj.web.extender.config.FilterConfig;
import net.hs.easyj.web.extender.config.ListenerConfig;
import net.hs.easyj.web.extender.config.ServletConfig;
import net.hs.easyj.web.extender.config.WebConfiguration;
import net.hs.easyj.web.extender.util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Gavin Hu on 2015/2/6.
 */
public class XmlWebConfiguration implements WebConfiguration {

    private Hashtable<String, String> contextParamMap = new Hashtable<String, String>();

    private Map<String, FilterConfig> filterConfigMap = new HashMap<String, FilterConfig>();

    private Map<String, ServletConfig> servletConfigMap = new HashMap<String, ServletConfig>();

    private List<ListenerConfig> listenerConfigList = new ArrayList<ListenerConfig>();

    public XmlWebConfiguration(InputStream webExtenderXml) {
        parse(webExtenderXml);
    }

    private void parse(InputStream webExtenderXml) {
        //
        try {
            Document webExtenderDoc = XmlUtils.parse(webExtenderXml);
            List<Element> contextParamEles = XmlUtils.getChildElementsByTagName(webExtenderDoc.getDocumentElement(), "context-param");
            for(Element contextParamEle : contextParamEles) {
                Element paramNameEle = XmlUtils.getChildElementByTagName(contextParamEle, "param-name");
                Element paramValueEle = XmlUtils.getChildElementByTagName(contextParamEle, "param-value");
                //
                contextParamMap.put(XmlUtils.getTextValue(paramNameEle), XmlUtils.getTextValue(paramValueEle));
            }
            //
            List<Element> filterEles = XmlUtils.getChildElementsByTagName(webExtenderDoc.getDocumentElement(), "filter");
            for(Element filterEle : filterEles) {
                //
                Element filterNameEle = XmlUtils.getChildElementByTagName(filterEle, "filter-name");
                Element filterClassEle = XmlUtils.getChildElementByTagName(filterEle, "filter-class");
                //
                FilterConfig filterConfig = new FilterConfig(XmlUtils.getTextValue(filterNameEle), XmlUtils.getTextValue(filterClassEle));
                filterConfigMap.put(filterConfig.getFilterName(), filterConfig);
                //
                List<Element> initParamEles = XmlUtils.getChildElementsByTagName(filterEle, "init-param");
                for(Element initParamEle : initParamEles) {
                    Element paramNameEle = XmlUtils.getChildElementByTagName(initParamEle, "param-name");
                    Element paramValueEle = XmlUtils.getChildElementByTagName(initParamEle, "param-value");
                    //
                    filterConfig.getInitParams().put(XmlUtils.getTextValue(paramNameEle), XmlUtils.getTextValue(paramValueEle));
                }
            }
            List<Element> filterMappingEles = XmlUtils.getChildElementsByTagName(webExtenderDoc.getDocumentElement(), "filter-mapping");
            for(Element filterMappingEle : filterMappingEles) {
                //
                Element filterNameEle = XmlUtils.getChildElementByTagName(filterMappingEle, "filter-name");
                Element servletNameEle = XmlUtils.getChildElementByTagName(filterMappingEle, "servlet-name");
                Element urlPatternEle = XmlUtils.getChildElementByTagName(filterMappingEle, "url-pattern");
                //
                FilterConfig filterConfig = filterConfigMap.get(XmlUtils.getTextValue(filterNameEle));
                if(servletNameEle!=null) {
                    filterConfig.setServletName(XmlUtils.getTextValue(servletNameEle));
                }
                if(urlPatternEle!=null) {
                    String urlPatternStr = XmlUtils.getTextValue(urlPatternEle);
                    String[] urlPatterns = urlPatternStr.split("\\|");
                    filterConfig.getUrlPatterns().addAll(Arrays.asList(urlPatterns));
                }
            }
            //
            List<Element> servletEles = XmlUtils.getChildElementsByTagName(webExtenderDoc.getDocumentElement(), "servlet");
            for(Element servletEle : servletEles) {
                //
                Element servletNameEle = XmlUtils.getChildElementByTagName(servletEle, "servlet-name");
                Element servletClassEle = XmlUtils.getChildElementByTagName(servletEle, "servlet-class");
                //
                ServletConfig servletConfig = new ServletConfig(XmlUtils.getTextValue(servletNameEle), XmlUtils.getTextValue(servletClassEle));
                servletConfigMap.put(servletConfig.getServletName(), servletConfig);
                //
                List<Element> initParamEles = XmlUtils.getChildElementsByTagName(servletEle, "init-param");
                for(Element initParamEle : initParamEles) {
                    Element paramNameEle = XmlUtils.getChildElementByTagName(initParamEle, "param-name");
                    Element paramValueEle = XmlUtils.getChildElementByTagName(initParamEle, "param-value");
                    //
                    servletConfig.getInitParams().put(XmlUtils.getTextValue(paramNameEle), XmlUtils.getTextValue(paramValueEle));
                }
            }
            //
            List<Element> servletMappingEles = XmlUtils.getChildElementsByTagName(webExtenderDoc.getDocumentElement(), "servlet-mapping");
            for(Element servletMappingEle : servletMappingEles) {
                //
                Element servletNameEle = XmlUtils.getChildElementByTagName(servletMappingEle, "servlet-name");
                Element urlPatternEle = XmlUtils.getChildElementByTagName(servletMappingEle, "url-pattern");
                //
                ServletConfig servletConfig = servletConfigMap.get(XmlUtils.getTextValue(servletNameEle));
                if(urlPatternEle!=null) {
                    String urlPatternStr = XmlUtils.getTextValue(urlPatternEle);
                    String[] urlPatterns = urlPatternStr.split("\\|");
                    servletConfig.getUrlPatterns().addAll(Arrays.asList(urlPatterns));
                }
            }
            //
            List<Element> listenerEles = XmlUtils.getChildElementsByTagName(webExtenderDoc.getDocumentElement(), "listener");
            for(Element listenerEle : listenerEles) {
                //
                Element listenerClassEle = XmlUtils.getChildElementByTagName(listenerEle, "listener-class");
                //
                ListenerConfig listenerConfig = new ListenerConfig(XmlUtils.getTextValue(listenerClassEle));
                listenerConfigList.add(listenerConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FilterConfig> getAllFilterConfig() {
        return new ArrayList<FilterConfig>(filterConfigMap.values());
    }

    @Override
    public List<ServletConfig> getAllServletConfig() {
        return new ArrayList<ServletConfig>(servletConfigMap.values());
    }

    @Override
    public List<ListenerConfig> getAllListenerConfig() {
        return listenerConfigList;
    }

    @Override
    public Hashtable<String, String> getContextParams() {
        return contextParamMap;
    }

}
