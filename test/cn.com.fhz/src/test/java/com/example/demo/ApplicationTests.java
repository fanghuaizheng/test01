package com.example.demo;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.dao.InfoDao;
import com.example.demo.entity.InfoDemo;
import com.example.demo.service.InfoService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ApplicationTests {

	
	@Autowired
	InfoDao dao;
	
	@Autowired
	InfoService infoService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void initValue() {
		
		for(int i=0;i<20;i++) {
			InfoDemo item = new InfoDemo(UUID.randomUUID().toString().replaceAll("-", ""),
					"name"+i,"value"+i,"code"+i,"info"+i,new Date(),new Date(),
					"1","desc"+i,null,null);
			
			dao.save(item);
		}
		
	}
	
	@Test
	public void findPage() {
		InfoDemo demo = new InfoDemo();
		demo.setCode("code1");
		Pageable page =  PageRequest.of(0,10);
		Page<InfoDemo> data = infoService.findAllByParam(demo, page);
		log.info("查询到数据={},total={}",data.getContent(),data.getNumber());
		
	}

}
