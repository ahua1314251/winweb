package org.winker.winweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class Index2 {



    @RequestMapping("pages/**")
    public String index2(HttpServletRequest res){
        System.out.println(res.getRequestURI());;
        return res.getRequestURI().replaceAll(".html","");
    }

    @RequestMapping("/")
    public String index(){


        return "/pages/tables/data";
    }

}
