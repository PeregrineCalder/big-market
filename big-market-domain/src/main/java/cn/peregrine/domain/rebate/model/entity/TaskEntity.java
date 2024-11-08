package cn.peregrine.domain.rebate.model.entity;

import cn.peregrine.domain.rebate.event.SendRebateMessageEvent;
import cn.peregrine.domain.rebate.model.valobj.TaskStateVO;
import cn.peregrine.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.rebate.model.entity
 * @className: TaskEntity
 * @author: Peregrine Calder
 * @description: 任务实体对象
 * @date: 2024/8/6 23:16
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
    private BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> message;
    /** 任务状态；create-创建、completed-完成、fail-失败 */
    private TaskStateVO state;


}
