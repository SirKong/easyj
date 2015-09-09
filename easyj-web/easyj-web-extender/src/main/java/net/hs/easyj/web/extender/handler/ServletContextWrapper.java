/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.hs.easyj.web.extender.handler;

import net.hs.easyj.web.extender.context.ExtServletContextImpl;
import net.hs.easyj.web.extender.dispatch.RequestDispatcherProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * @author <a href="mailto:dev@felix.apache.org">Felix Project Team</a>
 */
class ServletContextWrapper extends ExtServletContextImpl {
    private final RequestDispatcherProvider provider;

    /**
     * Creates a new {@link ServletContextWrapper} instance.
     */
    public ServletContextWrapper(ServletContext delegate, RequestDispatcherProvider provider) {
        super(delegate);

        this.provider = provider;
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        if (name == null) {
            return null;
        }

        RequestDispatcher dispatcher = this.provider.getNamedDispatcher(name);
        return dispatcher != null ? dispatcher : super.getNamedDispatcher(name);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        // See section 9.1 of Servlet 3.x specification...
        if (path == null || (!path.startsWith("/") && !"".equals(path))) {
            return null;
        }

        RequestDispatcher dispatcher = this.provider.getRequestDispatcher(path);
        return dispatcher != null ? dispatcher : super.getRequestDispatcher(path);
    }
}
