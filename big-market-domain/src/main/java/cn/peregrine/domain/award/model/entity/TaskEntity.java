package cn.peregrine.domain.award.model.entity;

import cn.peregrine.domain.award.event.SendAwardMessageEvent;
import cn.peregrine.domain.award.model.valobj.TaskStateVO;
import cn.peregrine.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.award.model.entity
 * @className: TaskEntity
 * @author: Peregrine Calder
 * @description: 任务实体对象
 * @date: 2024/5/1 19:40
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    /** 活动ID */
    private String userId;
    /** 消息主题 */
    private String topic;
    /** 消息编号 */
    private String messageId;
    /** 消息主体 */
    private BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> message;
    /** 任务状态；create-创建、completed-完成、fail-失败 */
    private TaskStateVO state;
}
