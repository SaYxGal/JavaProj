package com.labwork01.app.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticPagesMvcController {
    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }
    @RequestMapping("/forum")
    public String forumPage(){
        return "forum";
    }
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
}
