/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hs.easyj.web.extender.handler;

import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class HandlerRegistry {
    private final Map<String, ServletHandler> servletHandlerMap;
    private final Map<String, FilterHandler> filterHandlerMap;
    //private final Map<String, String> aliasMap;
    private ServletHandler[] servletHandlers;
    private FilterHandler[] filterHandlers;

    public HandlerRegistry() {
        this.servletHandlerMap = new HashMap<String, ServletHandler>();
        this.filterHandlerMap = new HashMap<String, FilterHandler>();
        //this.aliasMap = new HashMap<String, String>();
        this.servletHandlers = new ServletHandler[0];
        this.filterHandlers = new FilterHandler[0];
    }

    public ServletHandler[] getServletHandlers() {
        return this.servletHandlers;
    }

    public FilterHandler[] getFilterHandlers() {
        return this.filterHandlers;
    }

    public synchronized void addServlet(ServletHandler handler) throws ServletException {
        /*if (this.servletHandlerMap.containsKey(handler.getName())) {
            throw new ServletException("Servlet instance already registered");
        }*/

        if (this.servletHandlerMap.containsKey(handler.getName())) {
            throw new ServletException("Servlet with alias already registered");
        }
        //
        //handler.init();
        //
        this.servletHandlerMap.put(handler.getName(), handler);
        //
        updateServletArray();
    }

    public synchronized void addFilter(FilterHandler handler) throws ServletException {
        if (this.filterHandlerMap.containsKey(handler.getName())) {
            throw new ServletException("Filter instance already registered");
        }
        //
        //handler.init();
        //
        this.filterHandlerMap.put(handler.getName(), handler);
        //
        updateFilterArray();
    }

    public synchronized void removeServlet(String name, final boolean destroy) {
        ServletHandler handler = this.servletHandlerMap.remove(name);
        if (handler != null) {
            updateServletArray();
            //this.aliasMap.remove(handler.getAlias());
            if (destroy) {
                handler.destroy();
            }
        }
    }

    public synchronized void removeFilter(String name, final boolean destroy) {
        FilterHandler handler = this.filterHandlerMap.remove(name);
        if (handler != null) {
            updateFilterArray();
            if (destroy) {
                handler.destroy();
            }
        }
    }

    /*public synchronized Servlet getServletByAlias(String alias) {
        String name =  this.aliasMap.get(alias);
        ServletHandler servletHandler = servletHandlerMap.get(name);
        return servletHandler.getServlet();
    }*/

    public synchronized void removeAll() {
        for (ServletHandler handler : this.servletHandlerMap.values()) {
            handler.destroy();
        }

        for (FilterHandler handler : this.filterHandlerMap.values()) {
            handler.destroy();
        }

        this.servletHandlerMap.clear();
        this.filterHandlerMap.clear();
       // this.aliasMap.clear();

        updateServletArray();
        updateFilterArray();
    }

    public void init() throws ServletException {
        for(ServletHandler servletHandler : getServletHandlers()) {
            servletHandler.init();
        }
        for(FilterHandler filterHandler : getFilterHandlers()) {
            filterHandler.destroy();
        }
    }

    public void destroy() {
        for(ServletHandler servletHandler : getServletHandlers()) {
            servletHandler.destroy();
        }
        for(FilterHandler filterHandler : getFilterHandlers()) {
            filterHandler.destroy();
        }
    }

    private void updateServletArray() {
        ServletHandler[] tmp = this.servletHandlerMap.values().toArray(new ServletHandler[this.servletHandlerMap.size()]);
        Arrays.sort(tmp);
        this.servletHandlers = tmp;
    }

    private void updateFilterArray() {
        FilterHandler[] tmp = this.filterHandlerMap.values().toArray(new FilterHandler[this.filterHandlerMap.size()]);
        Arrays.sort(tmp);
        this.filterHandlers = tmp;
    }
}
