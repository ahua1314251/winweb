package demoWeb;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.winker.application.Application;
import org.winker.service.WinkerUserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import winker.dal.mapper.WinkerUserMapper;
import winker.dal.model.WinkerUser;


@RunWith(SpringRunner.class)
/*@ConfigurationProperties(prefix="application")*/
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration
public class MybatisTest {

	@Autowired
	WinkerUserMapper   winkerUserMapper ;
	@Autowired
	WinkerUserService   winkerUserService ;
	

	@Test
	public  void TEST() throws Exception{
		
		 ObjectMapper mapper = new ObjectMapper(); 
		 
		 
		WinkerUser record = new WinkerUser();
		
		record.setId(3233L);
		record.setLoginId("12312");
		
		record.setEmail("23123123");
		record.setPassword("123123");
		record.setUserName("shanfu.liu18");
		record.setAddress("qweqw");
		record.setUpdateTime(new Date());
		
		winkerUserService.insertUser(record,1234);
//		record.setId(record.getId()+1);
//		winkerUserService.insertUser(record,1234);
//		record.setId(record.getId()+1);
//		winkerUserService.insertUser(record,1234);
		
//		winkerUserService.updateUser(record,1234);
//		winkerUserService.updateUser(record,1234);
//		winkerUserService.updateUser(record,1234);
//		System.out.println(mapper.writeValueAsString(record));
//		record.setId(12L);
//		winkerUserService.updateUser(record,321);
//		winkerUserService.updateUser(record,1234);

//		record.setId(12L);
//		record.setUpdateTime(new Date());
//		winkerUserService.updateUser(record,5678);
//		throw new Exception("exception");
//		
		
	}
	
	@Test
	public  void asd(){
		Long a =12341231L;
		System.out.println(a.hashCode());
		
		String b="123456789";
		System.out.println(b.hashCode());
	}
	
	
}
