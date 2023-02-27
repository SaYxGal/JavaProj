package com.labwork01.app.actions.service;

import com.labwork01.app.actions.domain.ActionsInteger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.labwork01.app.actions.domain.IActions;

@Service
public class ActionService {
    private final ApplicationContext applicationContext;

    public ActionService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

   public String sum(Object first, Object second, String type) {
        final IActions actions = (IActions) applicationContext.getBean(type);
        if(actions instanceof ActionsInteger){
            return String.format("%s", actions.sum(Integer.parseInt(first.toString()), Integer.parseInt(second.toString())));
        }
        else{
            return String.format("%s", actions.sum(first, second));
        }
    }
    public String minus(Object first, Object second, String type) {
        final IActions actions = (IActions) applicationContext.getBean(type);
        if(actions instanceof ActionsInteger){
            return String.format("%s", actions.minus(Integer.parseInt(first.toString()), Integer.parseInt(second.toString())));
        }
        else{
            return String.format("%s", actions.minus(first, second));
        }
    }
    public String multiply(Object first, Object second, String type) {
        final IActions actions = (IActions) applicationContext.getBean(type);
        if(actions instanceof ActionsInteger){
            return String.format("%s", actions.multiply(Integer.parseInt(first.toString()), Integer.parseInt(second.toString())));
        }
        else{
            return String.format("%s", actions.multiply(first, second));
        }
    }
    public Boolean exact(Object first, Object second, String type) {
        final IActions actions = (IActions) applicationContext.getBean(type);
        if(actions instanceof ActionsInteger){
            return actions.isExactLength(Integer.parseInt(first.toString()), Integer.parseInt(second.toString()));
        }
        else{
            return actions.isExactLength(first, second);
        }
    }
}
