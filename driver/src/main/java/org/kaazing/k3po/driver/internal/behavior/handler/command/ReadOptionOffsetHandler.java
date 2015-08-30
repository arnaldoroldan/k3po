/*
 * Copyright 2014, Kaazing Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaazing.k3po.driver.internal.behavior.handler.command;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;
import static org.jboss.netty.channel.Channels.fireMessageReceived;

import java.util.List;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.kaazing.k3po.driver.internal.behavior.handler.codec.ConfigEncoder;
import org.kaazing.k3po.driver.internal.netty.bootstrap.file.FileChannel;

public class ReadOptionOffsetHandler extends AbstractCommandHandler {

    @Override
    protected void invokeCommand(ChannelHandlerContext ctx) throws Exception {
        try {
            FileChannel channel = (FileChannel) ctx.getChannel();
            channel.readOffset(35);      // TODO
            getHandlerFuture().setSuccess();

            fireMessageReceived(ctx, channel.channelBuffer, ctx.getChannel().getRemoteAddress());
        } catch (Exception e) {
            getHandlerFuture().setFailure(e);
        }
    }

    @Override
    public String toString() {
        return "read option offset";
    }

}
