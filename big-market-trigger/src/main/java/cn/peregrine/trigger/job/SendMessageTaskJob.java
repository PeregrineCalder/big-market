package cn.peregrine.trigger.job;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.peregrine.domain.task.model.entity.TaskEntity;
import cn.peregrine.domain.task.service.ITaskService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: big-market
 * @package: cn.peregrine.trigger.job
 * @className: SendMessageTaskJob
 * @author: Peregrine Calder
 * @description: 发送MQ消息任务队列
 * @date: 2024/5/1 20:44
 * @version: 1.0
 */
@Slf4j
@Component()
public class SendMessageTaskJob {
    @Resource
    private ITaskService taskService;
    @Resource
    private ThreadPoolExecutor executor;
    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private RedissonClient redissonClient;


    @XxlJob("SendMessageTaskJob_DB1")
    public void exec_db01() {
        RLock lock = redissonClient.getLock("big-market-SendMessageTaskJob_DB1");
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(3, 0, TimeUnit.SECONDS);
            if (!isLocked) return;

            dbRouter.setDBKey(1);
            dbRouter.setTBKey(0);
            List<TaskEntity> taskEntities = taskService.queryNoSendMessageTaskList();
            if (taskEntities.isEmpty()) return;
            for (TaskEntity taskEntity : taskEntities) {
                try {
                    taskService.sendMessage(taskEntity);
                    taskService.updateTaskSendMessageCompleted(taskEntity.getUserId(), taskEntity.getMessageId());
                } catch (Exception e) {
                    log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                    taskService.updateTaskSendMessageFail(taskEntity.getUserId(), taskEntity.getMessageId());
                }
            }
        } catch (Exception e) {
            log.error("定时任务，扫描MQ任务表发送消息失败。", e);
        } finally {
            dbRouter.clear();
            if (isLocked) {
                lock.unlock();
            }
        }
    }

    @XxlJob("SendMessageTaskJob_DB2")
    public void exec_db02() {
        RLock lock = redissonClient.getLock("big-market-SendMessageTaskJob_DB2");
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(3, 0, TimeUnit.SECONDS);
            if (!isLocked) return;

            dbRouter.setDBKey(2);
            dbRouter.setTBKey(0);
            List<TaskEntity> taskEntities = taskService.queryNoSendMessageTaskList();
            if (taskEntities.isEmpty()) return;
            for (TaskEntity taskEntity : taskEntities) {
                try {
                    taskService.sendMessage(taskEntity);
                    taskService.updateTaskSendMessageCompleted(taskEntity.getUserId(), taskEntity.getMessageId());
                } catch (Exception e) {
                    log.error("定时任务，发送MQ消息失败 userId: {} topic: {}", taskEntity.getUserId(), taskEntity.getTopic());
                    taskService.updateTaskSendMessageFail(taskEntity.getUserId(), taskEntity.getMessageId());
                }
            }
        } catch (Exception e) {
            log.error("定时任务，扫描MQ任务表发送消息失败。", e);
        } finally {
            dbRouter.clear();
            if (isLocked) {
                lock.unlock();
            }
        }
    }

}
