package cn.peregrine.test.domain.activity;

import cn.peregrine.domain.activity.model.entity.ActivityOrderEntity;
import cn.peregrine.domain.activity.model.entity.ActivityShopCartEntity;
import cn.peregrine.domain.activity.service.IRaffleOrder;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.domain.activity
 * @className: RaffleOrderTest
 * @author: Peregrine Calder
 * @description: 抽奖活动订单测试
 * @date: 2024/4/26 19:43
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleOrderTest {
    @Resource
    private IRaffleOrder raffleOrder;
    @Test
    public void test_createRaffleActivityOrder() {
        ActivityShopCartEntity activityShopCartEntity = new ActivityShopCartEntity();
        activityShopCartEntity.setUserId("xiaofuge");
        activityShopCartEntity.setSku(9011L);
        ActivityOrderEntity raffleActivityOrder = raffleOrder.createRaffleActivityOrder(activityShopCartEntity);
        log.info("测试结果：{}", JSON.toJSONString(raffleActivityOrder));
    }
}
