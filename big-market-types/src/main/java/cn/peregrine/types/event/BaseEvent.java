package cn.peregrine.types.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.types.event
 * @className: BaseEvent
 * @author: Peregrine Calder
 * @description: 基础时间
 * @date: 2024/4/27 22:57
 * @version: 1.0
 */
@Data
public abstract class BaseEvent<T> {
    public abstract EventMessage<T> buildEventMessage(T data);
    public abstract String topic();
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventMessage<T> {
        private String id;
        private Date timestamp;
        private T data;
    }
}
