package cn.peregrine.domain.activity.model.entity;

import cn.peregrine.domain.activity.model.valobj.ActivityStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.activity.model.entity
 * @className: ActivityEntity
 * @author: Peregrine Calder
 * @description: 活动实体对象
 * @date: 2024/4/25 23:17
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDesc;

    /**
     * 开始时间
     */
    private Date beginDateTime;

    /**
     * 结束时间
     */
    private Date endDateTime;

    /**
     * 活动参与次数配置
     */
    private Long activityCountId;

    /**
     * 抽奖策略ID
     */
    private Long strategyId;

    /**
     * 活动状态
     */
    private ActivityStateVO state;

}
