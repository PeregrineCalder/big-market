package cn.peregrine.domain.award.model.aggregate;

import cn.peregrine.domain.award.model.entity.UserAwardRecordEntity;
import cn.peregrine.domain.award.model.entity.UserCreditAwardEntity;
import cn.peregrine.domain.award.model.valobj.AwardStateVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.award.model.aggregate
 * @className: GiveOutPrizesAggregate
 * @author: Peregrine Calder
 * @description: 发放奖品聚合对象
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiveOutPrizesAggregate {

    /** 用户ID */
    private String userId;
    /** 用户发奖记录 */
    private UserAwardRecordEntity userAwardRecordEntity;
    /** 用户积分奖品 */
    private UserCreditAwardEntity userCreditAwardEntity;

    public static UserAwardRecordEntity buildDistributeUserAwardRecordEntity(String userId, String orderId, Integer awardId, AwardStateVO awardState) {
        UserAwardRecordEntity userAwardRecord = new UserAwardRecordEntity();
        userAwardRecord.setUserId(userId);
        userAwardRecord.setOrderId(orderId);
        userAwardRecord.setAwardId(awardId);
        userAwardRecord.setAwardState(awardState);
        return userAwardRecord;
    }

    public static UserCreditAwardEntity buildUserCreditAwardEntity(String userId, BigDecimal creditAmount) {
        return UserCreditAwardEntity.builder().userId(userId).creditAmount(creditAmount).build();
    }

}

