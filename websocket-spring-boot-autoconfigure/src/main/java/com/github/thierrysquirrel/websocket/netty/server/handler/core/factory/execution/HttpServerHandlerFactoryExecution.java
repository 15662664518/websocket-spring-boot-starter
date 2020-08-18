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
package com.github.thierrysquirrel.websocket.netty.server.handler.core.factory.execution;

import com.github.thierrysquirrel.websocket.netty.core.factory.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.websocket.netty.core.factory.execution.ThreadPoolFactoryExecution;
import com.github.thierrysquirrel.websocket.netty.server.thread.execution.HttpServerHandshakeThreadExecution;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: HttpServerHandlerFactoryExecution
 * Description:
 * date: 2020/8/17 23:38
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class HttpServerHandlerFactoryExecution {
    private HttpServerHandlerFactoryExecution() {
    }

    public static void httpServerHandshake(Channel channel, FullHttpRequest request, int maxFramePayloadLength, int readTimeoutMilli) {
        HttpServerHandshakeThreadExecution handshakeThreadExecution = new HttpServerHandshakeThreadExecution (channel, request, maxFramePayloadLength, readTimeoutMilli);
        ThreadPoolExecutor handshakeThreadPool = ThreadPoolFactoryConstant.HTTP_SERVER_HANDSHAKE_THREAD_POOL;
        ThreadPoolFactoryExecution.statsThread (handshakeThreadPool, handshakeThreadExecution);

    }
}
