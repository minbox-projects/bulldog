package org.minbox.framework.bulldog.queue.support;

import org.minbox.framework.bulldog.queue.LogQueueConstants;
import org.minbox.framework.bulldog.queue.LogQueueConsumer;
import org.minbox.framework.fulldog.core.pojo.LogDetails;
import org.minbox.framework.message.pipe.client.process.MessageProcessor;
import org.minbox.framework.message.pipe.core.Message;
import org.minbox.framework.util.JsonUtils;

/**
 * Consumption log queue message use by message pipe
 *
 * @author 恒宇少年
 */
public class MessagePipeLogQueueConsumer implements LogQueueConsumer, MessageProcessor {
    @Override
    public String bindingPipeName() {
        return LogQueueConstants.LOG_PIPE_QUEUE_NAME;
    }

    @Override
    public void take(LogDetails logDetails) {
        // TODO Log storage
    }

    /**
     * Process log messages in the message pipeline
     *
     * @param specificPipeName The name of message pipe
     * @param requestId        Consumer request ID
     * @param message          The log content message
     * @return Return "true" after successful processing
     */
    @Override
    public boolean processing(String specificPipeName, String requestId, Message message) {
        boolean execSuccessfully = true;
        try {
            byte[] logBytes = message.getBody();
            LogDetails logDetails = JsonUtils.fromJsonString(new String(logBytes));
            this.take(logDetails);
        } catch (Exception e) {
            execSuccessfully = false;
        }
        return execSuccessfully;
    }
}
