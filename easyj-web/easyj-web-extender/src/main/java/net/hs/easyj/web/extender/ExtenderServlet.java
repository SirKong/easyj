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
package net.hs.easyj.web.extender;

import net.hs.easyj.web.extender.dispatch.Dispatcher;
import net.hs.easyj.web.extender.fork.ForkableDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ExtenderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        //
        Dispatcher dispatcher = (Dispatcher) getServletContext().getAttribute(Dispatcher.class.getName());
        ForkableDispatcher forkableDispatcher = (ForkableDispatcher) getServletContext().getAttribute(ForkableDispatcher.class.getName());
        //
        if(forkableDispatcher.canFork(req, resp)) {
            forkableDispatcher.forkAndDispatch(req, resp);
        } else {
            dispatcher.dispatch(req, resp);
        }
    }

}
