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

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class ServletHandler extends AbstractHandler implements Comparable<ServletHandler> {
    private final String name;
    private final Servlet servlet;
    private final List<String> patterns;

    public ServletHandler(ServletContext context, Servlet servlet, String name, List<String> patterns) {
        super(context);
        this.servlet = servlet;
        this.name = name;
        this.patterns = patterns;
    }

    /*public String getAlias() {
        return this.alias;
    }*/

    public String getName() {
        return name;
    }

    public Servlet getServlet() {
        return this.servlet;
    }

    public void init() throws ServletException {
        ServletConfig config = new ServletConfigImpl(name, getContext(), getInitParams());
        this.servlet.init(config);
    }

    public void destroy() {
        this.servlet.destroy();
    }

    public boolean matches(String uri) {
        //
        if(uri==null) {
            uri = "/";
        }
        //
        boolean flag = false;
        for(String pattern : patterns) {
            if (pattern.equals("/")) {
                flag = uri.startsWith(pattern);
            } else if(pattern.startsWith("*")) {
                flag = uri.endsWith(pattern.substring(1));
            } else {
                flag = uri.equals(pattern) || uri.startsWith(pattern + "/");
            }
            //
            if(flag) {
                break;
            }
        }
        //
        return flag;
    }

    public boolean handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final boolean matches = matches(req.getPathInfo());
        if (matches) {
            doHandle(req, res);
        }

        return matches;
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // set a sensible status code in case handleSecurity returns false
        res.setStatus(HttpServletResponse.SC_OK);

        this.servlet.service(new ServletHandlerRequest(req, "/"), res);
    }

    public int compareTo(ServletHandler other) {
        return 0;
    }
}
