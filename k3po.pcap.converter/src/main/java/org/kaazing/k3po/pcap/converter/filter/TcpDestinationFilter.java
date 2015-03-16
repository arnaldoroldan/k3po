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

package org.kaazing.k3po.pcap.converter.filter;

import org.kaazing.k3po.pcap.converter.packet.Packet;

/**
 * Simple tcp filter that can be used to filter packets based on destination ip and port
 *
 */
public class TcpDestinationFilter implements Filter {

    private Integer port;
    private String ip;
    
    public TcpDestinationFilter(int port, String ip) {
        super();
        this.port = port;
        this.ip = ip;
    }

    @Override
    public boolean passesFilter(Packet pc) throws FilterFailureException {
        if(!pc.isTcp())
            return false;
        if(pc.getTcpPayloadSize() < 1)
            return false;
        if(!(pc.getTcpDestPort() == port))
            return false;
        if(!(pc.getDestIpAddr().equals(ip)))
            return false;
        return true;
    }
}
