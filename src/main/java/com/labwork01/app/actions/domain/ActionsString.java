package com.labwork01.app.actions.domain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "string")
public class ActionsString implements IActions<String>{
    private final Logger log = LoggerFactory.getLogger(ActionsString.class);
    @Override
    public String sum(String first, String second) {
        return first + second;
    }
    @Override
    public String minus(String first, String second) {
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < second.length(); i++) {
            char c = second.charAt(i);
            if(first.indexOf(c) != -1)
                first = first.replaceFirst(""+c,"");
            else
                postfix.append(c);
        }
        return first + postfix;
    }
    @Override
    public String multiply(String first, String second) {
        StringBuilder sb = new StringBuilder();
        int mem = 0;
        for(int i = 0; i < Math.min(first.length(),second.length()); ++i){
            sb.append(first.charAt(i)).append(second.charAt(i));
            mem++;
        }
        if(Math.min(first.length(),second.length()) == first.length()){
            sb.append(second.substring(mem));
        }
        else{
            sb.append(first.substring(mem));
        }
        return sb.toString();
    }
    @Override
    public Boolean isExactLength(String first, String second) {
        return first.length() == second.length();
    }
    @PostConstruct
    public void init() {
        log.info("ActionsString.init()");
    }
    @PreDestroy
    public void destroy() {
        log.info("ActionsString.destroy()");
    }
}
