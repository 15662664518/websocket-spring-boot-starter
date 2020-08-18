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
package com.github.thierrysquirrel.websocket.core.template;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;
import lombok.Data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * ClassName: WebsocketMessageTemplate 
 * Description: 
 * date: 2020/8/17 22:11
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class WebsocketMessageTemplate {
    private WebSocketFrame webSocketFrame;
    public WebsocketMessageTemplate(WebSocketFrame webSocketFrame) {
        this.webSocketFrame = webSocketFrame;
    }

    public String convertText() {
        return webSocketFrame.content ().toString (CharsetUtil.UTF_8);
    }

    public byte[] convertBytes() {
        return webSocketFrame.content ().array ();
    }

    public void transferTo(String filePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream (filePath);
             FileChannel fileChannel = fileOutputStream.getChannel ()) {
            fileChannel.write (webSocketFrame.content ().nioBuffer ());
        }
    }
}
