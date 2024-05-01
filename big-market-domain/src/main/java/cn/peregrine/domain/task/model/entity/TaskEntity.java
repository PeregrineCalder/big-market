package cn.peregrine.domain.task.model.entity;

import lombok.Data;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.task.model.entity
 * @className: TaskEntity
 * @author: Peregrine Calder
 * @description: 任务实体对象
 * @date: 2024/5/1 21:25
 * @version: 1.0
 */
@Data
public class TaskEntity {
    /** 活动ID */
    private String userId;
    /** 消息主题 */
    private String topic;
    /** 消息编号 */
    private String messageId;
    /** 消息主体 */
    private String message;
}
