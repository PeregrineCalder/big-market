package cn.peregrine.trigger.listener;

import cn.peregrine.domain.award.event.SendAwardMessageEvent;
import cn.peregrine.domain.award.model.entity.DistributeAwardEntity;
import cn.peregrine.domain.award.service.IAwardService;
import cn.peregrine.types.event.BaseEvent;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    @Resource
    private IAwardService awardService;

    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_award}"))
    public void listener(String message) {
        try {
            log.info("监听用户奖品发送消息 topic: {} message: {}", topic, message);
            BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage>>() {
            }.getType());
            SendAwardMessageEvent.SendAwardMessage sendAwardMessage = eventMessage.getData();

            // 发放奖品
            DistributeAwardEntity distributeAwardEntity = new DistributeAwardEntity();
            distributeAwardEntity.setUserId(sendAwardMessage.getUserId());
            distributeAwardEntity.setOrderId(sendAwardMessage.getOrderId());
            distributeAwardEntity.setAwardId(sendAwardMessage.getAwardId());
            distributeAwardEntity.setAwardConfig(sendAwardMessage.getAwardConfig());
            awardService.distributeAward(distributeAwardEntity);

        } catch (Exception e) {
            log.error("监听用户奖品发送消息，消费失败 topic: {} message: {}", topic, message);
            throw e;
        }
    }

}
