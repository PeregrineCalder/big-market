package cn.peregrine.domain.award.event;

import cn.peregrine.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.award.event
 * @className: SendAwardMessageEvent
 * @author: Peregrine Calder
 * @description: 用户奖品记录事件消息
 * @date: 2024/5/1 19:48
 * @version: 1.0
 */
@Component
public class SendAwardMessageEvent extends BaseEvent<SendAwardMessageEvent.SendAwardMessage> {
    @Value("${spring.rabbitmq.topic.send_award}")
    private String topic;
    @Override
    public EventMessage<SendAwardMessageEvent.SendAwardMessage> buildEventMessage(SendAwardMessageEvent.SendAwardMessage data) {
        return EventMessage.<SendAwardMessage>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(data)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendAwardMessage {
        /**
         * 用户ID
         */
        private String userId;
        /**
         * 奖品ID
         */
        private Integer awardId;
        /**
         * 奖品标题（名称）
         */
        private String awardTitle;
    }

}
