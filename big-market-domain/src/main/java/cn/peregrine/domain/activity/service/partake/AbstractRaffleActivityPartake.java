package cn.peregrine.domain.activity.service.partake;

import cn.peregrine.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import cn.peregrine.domain.activity.model.entity.ActivityEntity;
import cn.peregrine.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.peregrine.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.peregrine.domain.activity.model.valobj.ActivityStateVO;
import cn.peregrine.domain.activity.repository.IActivityRepository;
import cn.peregrine.domain.activity.service.IRaffleActivityPartakeService;
import cn.peregrine.types.enums.ResponseCode;
import cn.peregrine.types.exception.AppException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.service.partake
 * @className: AbstractRaffleActivityPartake
 * @author: Peregrine Calder
 * @description: 抽奖活动参与抽奖类
 * @date: 2024/4/28 16:40
 * @version: 1.0
 */
@Slf4j
public abstract class AbstractRaffleActivityPartake implements IRaffleActivityPartakeService {
    protected final IActivityRepository activityRepository;

    public AbstractRaffleActivityPartake(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity) {
        // 0. 基础信息
        String userId = partakeRaffleActivityEntity.getUserId();
        Long activityId = partakeRaffleActivityEntity.getActivityId();
        Date currentDate = new Date();
        // 1. 活动查询
        ActivityEntity activityEntity = activityRepository.queryRaffleActivityByActivityId(activityId);
        // 校验, 活动状态
        if (!ActivityStateVO.open.equals(activityEntity.getState())) {
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(), ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        // 校验, 活动日期「开始时间 <- 当前时间 -> 结束时间」
        if (activityEntity.getEndDateTime().before(currentDate) || activityEntity.getBeginDateTime().after(currentDate)) {
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(), ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }
        // 2. 查询未被使用的活动参与订单记录
        UserRaffleOrderEntity userRaffleOrderEntity = activityRepository.queryNoUsedRaffleOrder(partakeRaffleActivityEntity);
        if (userRaffleOrderEntity != null) {
            log.info("创建参与活动订单[已创建未消费] userId:{} activityId:{} userRaffleOrderEntity:{}", userId, activityId, JSON.toJSONString(userRaffleOrderEntity));
            return userRaffleOrderEntity;
        }
        // 3. 额度账户过滤&返回账户构建对象
        CreatePartakeOrderAggregate createPartakeOrderAggregate = this.doFilterAccount(userId, activityId, currentDate);
        // 4. 构建订单
        userRaffleOrderEntity = this.buildUserRaffleOrder(userId, activityId, currentDate);
        // 5. 填充抽奖单实体对象
        createPartakeOrderAggregate.setUserRaffleOrderEntity(userRaffleOrderEntity);
        // 6. 保存聚合对象 - 一个领域内的一个聚合是一个事务操作
        activityRepository.saveCreatePartakeOrderAggregate(createPartakeOrderAggregate);
        // 7. 返回订单信息
        return userRaffleOrderEntity;
    }

    @Override
    public UserRaffleOrderEntity createOrder(String userId, Long activityId) {
        return createOrder(PartakeRaffleActivityEntity.builder()
                .userId(userId)
                .activityId(activityId)
                .build());
    }

    protected abstract UserRaffleOrderEntity buildUserRaffleOrder(String userId, Long activityId, Date currentDate);

    protected abstract CreatePartakeOrderAggregate doFilterAccount(String userId, Long activityId, Date currentDate);
}
