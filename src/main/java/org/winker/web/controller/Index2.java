package org.winker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class Index2 {


    @RequestMapping("index.html")
    public String index(){

        return "calendar";
    }

    @RequestMapping("pages/**")
    public String index2(HttpServletRequest res){
        System.out.println(res.getRequestURI());;
        return res.getRequestURI().replaceAll(".html","");
    }

}
