package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.InfoDemo;

@Repository
public interface InfoDao extends JpaRepository<InfoDemo, String>,PagingAndSortingRepository<InfoDemo, String>{

}
