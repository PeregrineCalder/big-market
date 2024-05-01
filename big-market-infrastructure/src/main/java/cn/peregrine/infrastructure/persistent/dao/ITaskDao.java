package cn.peregrine.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.peregrine.infrastructure.persistent.po.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 任务表, 发送 MQ
 */
@Mapper
public interface ITaskDao {
    void insert(Task task);

    @DBRouter
    void updateTaskSendMessageCompleted(Task task);

    @DBRouter
    void updateTaskSendMessageFail(Task task);

    List<Task> queryNoSendMessageTaskList();
}
