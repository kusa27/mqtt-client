/**
 * Copyright (C) 2010-2011, FuseSource Corp.  All rights reserved.
 *
 *     http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fusesource.mqtt.client;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.hawtdispatch.DispatchQueue;

/**
 * <p>
 * </p>
 *
 * @author <a href="http://hiramchirino.com">Hiram Chirino</a>
 */
public class Message {

    private UTF8Buffer topic;
    private Buffer payload;
    private Runnable onComplete;
    private DispatchQueue queue;

    public Message(DispatchQueue queue, UTF8Buffer topic, Buffer payload, Runnable onComplete) {
        this.queue = queue;
        this.payload = payload;
        this.topic = topic;
        this.onComplete = onComplete;
    }

    public Buffer getPayload() {
        return payload;
    }

    public UTF8Buffer getTopic() {
        return topic;
    }

    public void ack() {
        if(onComplete!=null) {
            queue.execute(onComplete);
            onComplete = null;
        }
    }

}
