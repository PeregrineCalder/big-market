package cn.peregrine.domain.activity.event;

import cn.peregrine.types.event.BaseEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.event
 * @className: ActivitySkuStockZeroMessageEvent
 * @author: Peregrine Calder
 * @description: 活动 sku 库存清空消息
 * @date: 2024/4/27 22:52
 * @version: 1.0
 */
@Component
public class ActivitySkuStockZeroMessageEvent extends BaseEvent<Long> {
    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;

    @Override
    public EventMessage<Long> buildEventMessage(Long sku) {
        return EventMessage.<Long>builder()
                .id(RandomStringUtils.randomNumeric(11))
                .timestamp(new Date())
                .data(sku)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }
}
