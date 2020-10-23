package com.example.demo.service;

import org.springframework.data.domain.Example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InfoDao;
import com.example.demo.entity.InfoDemo;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	InfoDao infoDao;
	
	@Override
	public List<InfoDemo> findAll(InfoDemo eg){
		Example<InfoDemo> ex = Example.of(eg);
		return infoDao.findAll(ex);
//		return null;
	}
	
	@Override
	public Page<InfoDemo> findAllByParam(InfoDemo entity,Pageable pageable){
		Example<InfoDemo> eg = Example.of(entity);
		
		return infoDao.findAll(eg, pageable);
//		return null;
	}
}
