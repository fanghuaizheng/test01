package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.InfoDemo;

public interface InfoService {

	/**
	 * 查询全部数据
	 * @param pageable
	 * @return
	 */
	List<InfoDemo> findAll(InfoDemo eg);
	
	/**
	 * 根据实例条件查询数据
	 * @param entity
	 * @param pageable
	 * @return
	 */
	Page<InfoDemo> findAllByParam(InfoDemo entity,Pageable pageable);
}
