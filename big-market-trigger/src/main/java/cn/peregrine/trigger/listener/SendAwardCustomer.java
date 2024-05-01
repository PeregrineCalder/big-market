package cn.peregrine.trigger.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.listener
 * @className: SendAwardCustomer
 * @author: Peregrine Calder
 * @description: 用户奖品记录消息消费者
 * @date: 2024/5/1 22:16
 * @version: 1.0
 */
@Slf4j
@Component
public class SendAwardCustomer {
    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;
    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_award}"))
    public void listener(String message) {
        try {
            log.info("监听用户奖品发送消息 topic: {} message: {}", topic, message);
        } catch (Exception e) {
            log.error("监听用户奖品发送消息，消费失败 topic: {} message: {}", topic, message);
            throw e;
        }
    }

}
