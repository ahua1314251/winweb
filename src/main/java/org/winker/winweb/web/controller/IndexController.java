package org.winker.winweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class IndexController {



    @RequestMapping("page/**")
    public String index2(HttpServletRequest res){
        System.out.println(res.getRequestURI());;
        return res.getRequestURI().replaceAll(".html","");
    }

    @RequestMapping("user/login")
    public String index(){


//        return "/pages/index";
        return "index";
    }

    @RequestMapping("admin/sub-page")
    public String index11(){


//        return "/pages/index";
        return "index";
    }

}
