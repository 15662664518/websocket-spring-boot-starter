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
package com.github.thierrysquirrel.websocket.netty.server.handler.core.factory.constant;

/**
 * ClassName: IdleStateConstant
 * Description:
 * date: 2020/8/17 21:33
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class IdleStateConstant {
    public static final int HTTP_READER_IDLE_TIME=1000;
    public static final int HTTP_WRITER_IDLE_TIME=0;
    public static final int HTTP_ALL_IDLE_TIME = 0;
    public static final int HTTP_MAX_CONTENT_LENGTH=0;

    public static final int WEBSOCKET_WRITER_IDLE_TIME = 0;
    public static final int WEBSOCKET_ALL_IDLE_TIME=0;

    private IdleStateConstant() {
    }
}
