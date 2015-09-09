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

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class FilterHandler extends AbstractHandler implements Comparable<FilterHandler> {
    private final Filter filter;
    private final String name;
    private final List<String> patterns;
    private final int ranking;

    public FilterHandler(ServletContext context, Filter filter, String name, List<String> patterns, int ranking) {
        super(context);
        this.name = name;
        this.filter = filter;
        this.ranking = ranking;
        this.patterns = patterns;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public void init() throws ServletException {
        FilterConfig config = new FilterConfigImpl(name, getContext(), getInitParams());
        this.filter.init(config);
    }

    public void destroy() {
        this.filter.destroy();
    }

    public boolean matches(String uri) {
        // assume root if uri is null
        if (uri == null) {
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

    public void handle(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        final boolean matches = matches(req.getPathInfo());
        if (matches) {
            doHandle(req, res, chain);
        } else {
            chain.doFilter(req, res);
        }
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        //
        this.filter.doFilter(req, res, chain);
    }

    public int compareTo(FilterHandler other) {
        if (other.ranking == this.ranking) {
            return 0;
        }

        return (other.ranking > this.ranking) ? 1 : -1;
    }

    public int getRanking() {
        return ranking;
    }

    public String getName() {
        return name;
    }
}
