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
package com.github.thierrysquirrel.websocket.netty.server.init;

import com.github.thierrysquirrel.websocket.netty.core.factory.SocketAddressFactory;
import com.github.thierrysquirrel.websocket.netty.server.handler.HttpServerInitChannelHandler;
import com.github.thierrysquirrel.websocket.netty.server.handler.core.factory.constant.IdleStateConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Data;

/**
 * ClassName: WebsocketServerInit
 * Description:
 * date: 2020/8/17 23:44
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class WebsocketServerInit {
    private String url;
    private int websocketMaxFramePayloadLength;
    private int websocketReadTimeoutMilli;

    public WebsocketServerInit(String url, int websocketMaxFramePayloadLength, int websocketReadTimeoutMilli) {
        this.url = url;
        this.websocketMaxFramePayloadLength = websocketMaxFramePayloadLength;
        this.websocketReadTimeoutMilli = websocketReadTimeoutMilli;
    }

    public void init() throws InterruptedException {
        new ServerBootstrap ().group (new NioEventLoopGroup (), new NioEventLoopGroup ())
                .channel (NioServerSocketChannel.class)
                .childHandler (new HttpServerInitChannelHandler (IdleStateConstant.HTTP_READER_IDLE_TIME,
                        IdleStateConstant.HTTP_WRITER_IDLE_TIME,
                        IdleStateConstant.HTTP_ALL_IDLE_TIME,
                        IdleStateConstant.HTTP_MAX_CONTENT_LENGTH,
                        websocketMaxFramePayloadLength,
                        websocketReadTimeoutMilli))
                .bind (SocketAddressFactory.getSocketAddress (url))
                .sync ()
                .channel ()
                .closeFuture ()
                .sync ();

    }
}
