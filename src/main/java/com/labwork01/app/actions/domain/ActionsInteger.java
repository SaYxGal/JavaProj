package com.labwork01.app.actions.domain;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component(value = "int")
public class ActionsInteger implements IActions<Integer> {
    private final Logger log = LoggerFactory.getLogger(ActionsInteger.class);
    @Override
    public Integer sum(Integer first, Integer second) {
        return first + second;
    }
    @Override
    public Integer minus(Integer first, Integer second) {
        return first - second;
    }

    @Override
    public Integer multiply(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Boolean isExactLength(Integer first, Integer second) {
        if(Objects.equals(first, second)){
            return true;
        }
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("ActionsInteger.init()");
    }
    @PreDestroy
    public void destroy() {
        log.info("ActionsInteger.destroy()");
    }
}
