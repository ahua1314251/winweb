package org.winker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class Index2 {

	
	@RequestMapping("/index2")
	public String index2( Model model,String name) {
		System.out.println(name);
		model.addAttribute("now", name);
		return "index3";
	}
	
}
