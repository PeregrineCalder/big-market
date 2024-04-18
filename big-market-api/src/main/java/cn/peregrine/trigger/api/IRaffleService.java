package cn.peregrine.trigger.api;

import cn.peregrine.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.peregrine.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.peregrine.trigger.api.dto.RaffleRequestDTO;
import cn.peregrine.trigger.api.dto.RaffleResponseDTO;
import cn.peregrine.types.model.Response;

import java.util.List;

/**
 * @author: Peregrine Calder
 * @description: 抽奖服务接口
 * @version: 1.0
 */
public interface IRaffleService {
    /**
     * 策略装配接口
     * @param strategyId 策略 ID
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * 查询抽奖奖品列表配置
     *
     * @param requestDTO 抽奖奖品列表查询请求参数
     * @return 奖品列表数据
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    /**
     * 随机抽奖接口
     *
     * @param requestDTO 请求参数
     * @return 抽奖结果
     */
    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO requestDTO);
}
