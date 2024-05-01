package cn.peregrine.domain.award.service;

import cn.peregrine.domain.award.event.SendAwardMessageEvent;
import cn.peregrine.domain.award.model.aggregate.UserAwardRecordAggregate;
import cn.peregrine.domain.award.model.entity.TaskEntity;
import cn.peregrine.domain.award.model.entity.UserAwardRecordEntity;
import cn.peregrine.domain.award.model.valobj.TaskStateVO;
import cn.peregrine.domain.award.repository.IAwardRepository;
import cn.peregrine.types.event.BaseEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.award.service
 * @className: AwardService
 * @author: Peregrine Calder
 * @description: 奖品服务
 * @date: 2024/5/1 19:39
 * @version: 1.0
 */
@Service
public class AwardService implements IAwardService{
    @Resource
    private IAwardRepository awardRepository;
    @Resource
    private SendAwardMessageEvent sendAwardMessageEvent;

    @Override
    public void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity) {
        // 构建消息对象
        SendAwardMessageEvent.SendAwardMessage sendAwardMessage = new SendAwardMessageEvent.SendAwardMessage();
        sendAwardMessage.setAwardId(userAwardRecordEntity.getAwardId());
        sendAwardMessage.setUserId(userAwardRecordEntity.getUserId());
        sendAwardMessage.setAwardTitle(userAwardRecordEntity.getAwardTitle());
        BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> sendAwardMessageEventMessage = sendAwardMessageEvent.buildEventMessage(sendAwardMessage);

        // 构建任务对象
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(userAwardRecordEntity.getUserId());
        taskEntity.setTopic(sendAwardMessageEvent.topic());
        taskEntity.setMessageId(sendAwardMessageEventMessage.getId());
        taskEntity.setMessage(sendAwardMessageEventMessage);
        taskEntity.setState(TaskStateVO.create);

        // 构建聚合对象
        UserAwardRecordAggregate userAwardRecordAggregate = UserAwardRecordAggregate.builder()
                .taskEntity(taskEntity)
                .userAwardRecordEntity(userAwardRecordEntity)
                .build();

        // 存储聚合对象 - 一个事务下，用户的中奖记录
        awardRepository.saveUserAwardRecord(userAwardRecordAggregate);
    }
}
