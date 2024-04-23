package cn.peregrine.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.infrastructure.persistent.po
 * @className: RaffleActivityAccount
 * @author: Peregrine Calder
 * @description: 抽奖活动账户表 持久化对象
 * @date: 2024/4/23 22:17
 * @version: 1.0
 */
@Data
public class RaffleActivityAccount {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 总次数
     */
    private Integer totalCount;

    /**
     * 总次数-剩余
     */
    private Integer totalCountSurplus;

    /**
     * 日次数
     */
    private Integer dayCount;

    /**
     * 日次数-剩余
     */
    private Integer dayCountSurplus;

    /**
     * 月次数
     */
    private Integer monthCount;

    /**
     * 月次数-剩余
     */
    private Integer monthCountSurplus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
