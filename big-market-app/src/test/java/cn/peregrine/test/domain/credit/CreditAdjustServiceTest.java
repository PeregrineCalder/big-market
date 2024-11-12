package cn.peregrine.test.domain.credit;

import cn.peregrine.domain.credit.model.entity.TradeEntity;
import cn.peregrine.domain.credit.model.valobj.TradeNameVO;
import cn.peregrine.domain.credit.model.valobj.TradeTypeVO;
import cn.peregrine.domain.credit.service.ICreditAdjustService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.domain.credit
 * @className: CreditAdjustServiceTest
 * @author: Peregrine Calder
 * @description: 积分额度增加服务测试
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditAdjustServiceTest {

    @Resource
    private ICreditAdjustService creditAdjustService;

    @Test
    public void test_createOrder_forward() {
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setUserId("peregrine");
        tradeEntity.setTradeName(TradeNameVO.REBATE);
        tradeEntity.setTradeType(TradeTypeVO.FORWARD);
        tradeEntity.setAmount(new BigDecimal("2"));
        tradeEntity.setOutBusinessNo("100009909911");
        creditAdjustService.createOrder(tradeEntity);
    }

    @Test
    public void test_createOrder_reverse() {
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setUserId("peregrine");
        tradeEntity.setTradeName(TradeNameVO.REBATE);
        tradeEntity.setTradeType(TradeTypeVO.REVERSE);
        tradeEntity.setAmount(new BigDecimal("-1"));
        tradeEntity.setOutBusinessNo("20000990991");
        creditAdjustService.createOrder(tradeEntity);
    }

}

