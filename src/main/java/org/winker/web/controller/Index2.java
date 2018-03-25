package org.winker.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import winker.dal.mapper.WinkerUserMapper;
import winker.dal.model.WinkerUser;


@Controller
@RequestMapping("/")
public class Index2 {


	@Autowired
	WinkerUserMapper winkerUserMapper ;
	@RequestMapping("/index2")
	@Transactional(value="transactionManager")
	public String index2( Model model,String name) {
		System.out.println(name);
		model.addAttribute("now", name);
		
		
		 
			WinkerUser record = new WinkerUser();
	
		record.setLoginId("22222");
		
		record.setEmail("23123123");
		record.setPassword("123123");
		record.setUserName("shanfu.liu18");
		record.setAddress("qweqw");
		record.setUpdateTime(new Date());
		winkerUserMapper.insertSelective(record);

		return "index3";
	}
	
}
