package cn.peregrine.domain.strategy.service;

import cn.peregrine.domain.strategy.model.entity.RaffleAwardEntity;
import cn.peregrine.domain.strategy.model.entity.RaffleFactorEntity;
import cn.peregrine.domain.strategy.model.entity.RuleActionEntity;
import cn.peregrine.domain.strategy.model.entity.StrategyEntity;
import cn.peregrine.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.peregrine.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import cn.peregrine.domain.strategy.repository.IStrategyRepository;
import cn.peregrine.domain.strategy.service.IRaffleStrategy;
import cn.peregrine.domain.strategy.service.armory.IStrategyDispatch;
import cn.peregrine.domain.strategy.service.rule.chain.ILogicChain;
import cn.peregrine.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.peregrine.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import cn.peregrine.types.enums.ResponseCode;
import cn.peregrine.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.raffle
 * @className: AbstractRaffleStrategy
 * @author: Peregrine Calder
 * @description: 抽奖策略抽象类，定义抽奖的标准流程
 * @date: 2024/4/12 15:47
 * @version: 1.0
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {
    // 策略仓储服务
    protected IStrategyRepository repository;
    // 策略调度服务 -> 只负责抽奖处理，通过新增接口的方式，隔离职责，不需要使用方关心或者调用抽奖的初始化
    protected IStrategyDispatch strategyDispatch;
    private DefaultChainFactory defaultChainFactory;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory) {
        this.repository = repository;
        this.strategyDispatch = strategyDispatch;
        this.defaultChainFactory = defaultChainFactory;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (strategyId == null || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        // 责任链抽奖处理
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        Integer awardId = logicChain.logic(userId, strategyId);
        // 查询奖品规则 [抽奖中 (拿到奖品ID时，过滤规则)、抽奖后 (扣减完奖品库存后过滤，抽奖中拦截和无库存则走兜底)]
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModels(strategyId, awardId);
        // 抽奖中 - 规则过滤
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> ruleActionCenterEntity = this.doCheckRaffleCenterLogic(RaffleFactorEntity.builder()
                        .userId(userId)
                        .strategyId(strategyId)
                        .awardId(awardId)
                        .build(), strategyAwardRuleModelVO.raffleCenterRuleModelList());
        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionCenterEntity.getCode())) {
            log.info("临时日志 - 中奖中规则拦截, 通过抽奖后规则 rule_luck_award 走兜底奖励");
            return RaffleAwardEntity.builder()
                    .awardDesc("中奖中规则拦截, 通过抽奖后规则 rule_luck_award 走兜底奖励")
                    .build();
        }
        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();
    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);
    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

}
