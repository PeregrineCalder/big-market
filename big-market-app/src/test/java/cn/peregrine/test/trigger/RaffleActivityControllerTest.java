package cn.peregrine.test.trigger;

import cn.peregrine.trigger.api.IRaffleActivityService;
import cn.peregrine.trigger.api.dto.ActivityDrawRequestDTO;
import cn.peregrine.trigger.api.dto.ActivityDrawResponseDTO;
import cn.peregrine.types.model.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @projectName: big-market
 * @package: cn.peregrine.test.trigger
 * @className: RaffleActivityControllerTest
 * @author: Peregrine Calder
 * @description: 抽奖活动服务测试
 * @date: 2024/5/2 21:31
 * @version: 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityControllerTest {
    @Resource
    private IRaffleActivityService raffleActivityService;

    @Test
    public void test_armory() {
        Response<Boolean> response = raffleActivityService.armory(100301L);
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_draw() {
        ActivityDrawRequestDTO request = new ActivityDrawRequestDTO();
        request.setActivityId(100301L);
        request.setUserId("xiaofuge");
        Response<ActivityDrawResponseDTO> response = raffleActivityService.draw(request);

        log.info("请求参数：{}", JSON.toJSONString(request));
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

}
