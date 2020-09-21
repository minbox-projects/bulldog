package org.minbox.framework.bulldog.queue.support;

import org.minbox.framework.bulldog.common.utils.Assert;
import org.minbox.framework.bulldog.queue.LogQueueConstants;
import org.minbox.framework.bulldog.queue.LogQueueProvider;
import org.minbox.framework.fulldog.core.pojo.LogDetails;
import org.minbox.framework.message.pipe.core.Message;
import org.minbox.framework.message.pipe.server.MessagePipe;
import org.minbox.framework.message.pipe.server.manager.MessagePipeManager;
import org.minbox.framework.util.JsonUtils;

/**
 * Implement MessagePipe log queue
 * <p>
 * MessagePipe is distributed message sequence pipeline for load balancing based on Redis client Redisson
 * GitHub："https://github.com/minbox-projects/message-pipe"
 *
 * @author 恒宇少年
 */
public class MessagePipeLogQueueProvider implements LogQueueProvider {
    private MessagePipeManager manager;

    public MessagePipeLogQueueProvider(MessagePipeManager manager) {
        this.manager = manager;
    }

    @Override
    public void put(LogDetails logDetails) {
        Assert.notNull(logDetails, "The LogDetails cannot be null.");
        byte[] logBytes = JsonUtils.toJsonString(logDetails).getBytes();
        Message message = new Message(logBytes);
        MessagePipe messagePipe = manager.getMessagePipe(LogQueueConstants.LOG_PIPE_QUEUE_NAME);
        messagePipe.putLast(message);
    }
}
