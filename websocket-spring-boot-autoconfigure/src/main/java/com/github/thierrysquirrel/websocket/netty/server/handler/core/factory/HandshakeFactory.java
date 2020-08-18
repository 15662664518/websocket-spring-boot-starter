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
package com.github.thierrysquirrel.websocket.netty.server.handler.core.factory;

import com.github.thierrysquirrel.websocket.core.container.WebsocketRouteContainer;
import com.github.thierrysquirrel.websocket.core.domain.HttpUpgradeRequest;
import com.github.thierrysquirrel.websocket.core.domain.builder.HttpUpgradeRequestBuilder;
import com.github.thierrysquirrel.websocket.core.exception.WebsocketException;
import com.github.thierrysquirrel.websocket.core.factory.UriFactory;
import com.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;
import com.github.thierrysquirrel.websocket.core.template.core.factory.WebsocketRouteTemplateFactory;
import com.github.thierrysquirrel.websocket.netty.server.handler.core.factory.constant.HandshakeFactoryConstant;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

import java.util.Map;

/**
 * ClassName: HandshakeFactory
 * Description:
 * date: 2020/8/17 21:00
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class HandshakeFactory {
    private HandshakeFactory() {
    }

    public static WebsocketRouteTemplate pathValidation(String requestUri) throws WebsocketException {
        return WebsocketRouteContainer.getWebsocketRouteTemplate (UriFactory.conversionRouteUri (requestUri));
    }

    public static void handshake(FullHttpRequest request, int maxFramePayloadLength, Channel channel) throws WebsocketException {
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory (
                getWebSocketUrl (request), null, Boolean.TRUE, maxFramePayloadLength);
        WebSocketServerHandshaker wsHandshake = wsFactory.newHandshaker (request);
        if (null == wsHandshake) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse (channel);
            throw new WebsocketException ("Handshake Fail");
        }
        wsHandshake.handshake (channel, request);
    }

    public static void upgradePipeline(WebsocketRouteTemplate websocketRouteTemplate, int readTimeoutMilli, Channel channel, FullHttpRequest request) throws WebsocketException {
        String requestUri = request.uri ();
        String route = WebsocketRouteTemplateFactory.getRoute (websocketRouteTemplate);
        Map<String, String> uriVariable = UriFactory.getUriVariable (route, requestUri);
        Map<String, String> uriParamMap = UriFactory.getUriParamMap (requestUri);
        HttpUpgradeRequest httpUpgradeRequest = HttpUpgradeRequestBuilder.builderHttpUpgradeRequest (request.headers (), uriVariable, uriParamMap);
        ChannelPipelineFactory.upgradeWebsocketPipeline (channel, readTimeoutMilli, httpUpgradeRequest, websocketRouteTemplate);
    }

    private static String getWebSocketUrl(FullHttpRequest request) {
        return HandshakeFactoryConstant.WEBSOCKET_PROTOCOL + request.headers ().get (HttpHeaderNames.HOST) + request.uri ();
    }
}
