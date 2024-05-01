package cn.peregrine.domain.task.service;

import cn.peregrine.domain.task.model.entity.TaskEntity;
import cn.peregrine.domain.task.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.task.service
 * @className: TaskService
 * @author: Peregrine Calder
 * @description: 消息任务服务
 * @date: 2024/5/1 21:24
 * @version: 1.0
 */
@Service
public class TaskService implements ITaskService {
    @Resource
    private ITaskRepository taskRepository;

    @Override
    public List<TaskEntity> queryNoSendMessageTaskList() {
        return taskRepository.queryNoSendMessageTaskList();
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        taskRepository.sendMessage(taskEntity);
    }

    @Override
    public void updateTaskSendMessageCompleted(String userId, String messageId) {
        taskRepository.updateTaskSendMessageCompleted(userId, messageId);
    }

    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        taskRepository.updateTaskSendMessageFail(userId, messageId);
    }
}
