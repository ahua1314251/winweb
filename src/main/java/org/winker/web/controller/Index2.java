package org.winker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class Index2 {



    @RequestMapping("index.json")
    @ResponseBody
    public String index(){

        return "hello world!!123333";
    }

	
}
