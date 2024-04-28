package cn.peregrine.domain.activity.model.aggregate;

import cn.peregrine.domain.activity.model.entity.ActivityAccountDayEntity;
import cn.peregrine.domain.activity.model.entity.ActivityAccountEntity;
import cn.peregrine.domain.activity.model.entity.ActivityAccountMonthEntity;
import cn.peregrine.domain.activity.model.entity.UserRaffleOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.model.aggregate
 * @className: CreatePartakeOrderAggregate
 * @author: Peregrine Calder
 * @description: 参与活动订单聚合对象
 * @date: 2024/4/28 17:41
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePartakeOrderAggregate {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 账户总额度
     */
    private ActivityAccountEntity activityAccountEntity;

    /**
     * 是否存在月账户
     */
    private boolean isExistAccountMonth = true;

    /**
     * 账户月额度
     */
    private ActivityAccountMonthEntity activityAccountMonthEntity;

    /**
     * 是否存在日账户
     */
    private boolean isExistAccountDay = true;

    /**
     * 账户日额度
     */
    private ActivityAccountDayEntity activityAccountDayEntity;

    /**
     * 抽奖单实体
     */
    private UserRaffleOrderEntity userRaffleOrderEntity;

}
