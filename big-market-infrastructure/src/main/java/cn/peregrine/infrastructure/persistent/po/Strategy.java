package cn.peregrine.infrastructure.persistent.po;

import lombok.Data;
import java.util.Date;

/**
 * @projectName: big-market
 * @package: cn.peregrine.infrastructure.persistent.po
 * @className: Strategy
 * @author: Peregrine Calder
 * @description: 抽奖策略
 * @date: 2024/3/3 17:22
 * @version: 1.0
 */
@Data
public class Strategy {
    /** 自增 ID */
    private Long id;
    /** 抽奖策略 ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
