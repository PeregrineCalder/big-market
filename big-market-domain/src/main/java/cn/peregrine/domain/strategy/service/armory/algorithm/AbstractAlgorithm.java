package cn.peregrine.domain.strategy.service.armory.algorithm;

import cn.peregrine.domain.strategy.repository.IStrategyRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import java.security.SecureRandom;

/**
 * @projectName: big-market
 * @package: cn.peregrine.domain.strategy.service.armory.algorithm
 * @className: AbstractAlgorithm
 * @author: Peregrine Calder
 * @version: 1.0
 */
public abstract class AbstractAlgorithm implements IAlgorithm {
    @Resource
    protected IStrategyRepository repository;

    protected final SecureRandom secureRandom = new SecureRandom();

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum Algorithm {
        O1("o1Algorithm"), OLogN("oLogNAlgorithm");

        private String key;
    }

}
