package cn.peregrine.domain.award.model.aggregate;

import cn.peregrine.domain.award.model.entity.TaskEntity;
import cn.peregrine.domain.award.model.entity.UserAwardRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.award.model.aggregate
 * @className: UserAwardRecordAggregate
 * @author: Peregrine Calder
 * @description: 用户中奖记录聚合对象
 * @date: 2024/5/1 19:42
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAwardRecordAggregate {
    private UserAwardRecordEntity userAwardRecordEntity;
    private TaskEntity taskEntity;
}
