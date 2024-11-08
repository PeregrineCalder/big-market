package cn.peregrine.test.domain.rebate;

import cn.peregrine.domain.rebate.model.entity.BehaviorEntity;
import cn.peregrine.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.peregrine.domain.rebate.service.IBehaviorRebateService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.rebate
 * @className: BehaviorRebateServiceTest
 * @author: Peregrine Calder
 * @description: 行为返利单测
 * @date: 2024/8/27 09:39
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BehaviorRebateServiceTest {
    @Resource
    private IBehaviorRebateService behaviorRebateService;
    @Test
    public void test_createOrder() throws InterruptedException {
        BehaviorEntity behaviorEntity = new BehaviorEntity();
        behaviorEntity.setUserId("peregrine");
        behaviorEntity.setBehaviorTypeVO(BehaviorTypeVO.SIGN);
        behaviorEntity.setOutBusinessNo("20240828");
        List<String> orderIds = behaviorRebateService.createOrder(behaviorEntity);
        log.info("请求参数：{}", JSON.toJSONString(behaviorEntity));
        log.info("测试结果：{}", JSON.toJSONString(orderIds));
        new CountDownLatch(1).await();
    }
}
