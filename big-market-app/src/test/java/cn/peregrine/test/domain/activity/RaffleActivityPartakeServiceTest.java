package cn.peregrine.test.domain.activity;

import cn.peregrine.domain.activity.model.entity.PartakeRaffleActivityEntity;
import cn.peregrine.domain.activity.model.entity.UserRaffleOrderEntity;
import cn.peregrine.domain.activity.service.IRaffleActivityPartakeService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.domain.activity
 * @className: RaffleActivityPartakeServiceTest
 * @author: Peregrine Calder
 * @description: 抽奖活动订单单测
 * @date: 2024/4/28 21:42
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityPartakeServiceTest {
    @Resource
    private IRaffleActivityPartakeService raffleActivityPartakeService;
    @Test
    public void test_createOrder() {
        // 请求参数
        PartakeRaffleActivityEntity partakeRaffleActivityEntity = new PartakeRaffleActivityEntity();
        partakeRaffleActivityEntity.setUserId("xiaofuge");
        partakeRaffleActivityEntity.setActivityId(100301L);
        // 调用接口
        UserRaffleOrderEntity userRaffleOrder = raffleActivityPartakeService.createOrder(partakeRaffleActivityEntity);
        log.info("请求参数：{}", JSON.toJSONString(partakeRaffleActivityEntity));
        log.info("测试结果：{}", JSON.toJSONString(userRaffleOrder));
    }
}
