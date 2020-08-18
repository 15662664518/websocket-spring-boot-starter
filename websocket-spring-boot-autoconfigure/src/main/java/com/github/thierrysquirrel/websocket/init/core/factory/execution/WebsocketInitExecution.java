/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.websocket.init.core.factory.execution;

import com.github.thierrysquirrel.websocket.netty.core.factory.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.websocket.netty.core.factory.execution.ThreadPoolFactoryExecution;
import com.github.thierrysquirrel.websocket.netty.server.thread.execution.WebsocketServerInitThreadExecution;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: WebsocketInitExecution 
 * Description: 
 * date: 2020/8/18 2:09
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class WebsocketInitExecution {
    private WebsocketInitExecution() {
    }
    public static void init(String url, int websocketMaxFramePayloadLength, int websocketReadTimeoutMilli){
        WebsocketServerInitThreadExecution initThreadExecution=new WebsocketServerInitThreadExecution (url, websocketMaxFramePayloadLength, websocketReadTimeoutMilli);
        ThreadPoolExecutor initThreadPool = ThreadPoolFactoryConstant.WEBSOCKET_SERVER_INIT_THREAD_POOL;
        ThreadPoolFactoryExecution.statsThreadAndShutdown (initThreadPool,initThreadExecution);
    }
}
