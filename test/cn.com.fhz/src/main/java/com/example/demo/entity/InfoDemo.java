package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="t_info")
@AllArgsConstructor
@NoArgsConstructor
public class InfoDemo {
	
	@Id
	@Column(length = 32)
	@ExcelProperty("ID")
	private String id;
	
	@Column(length = 50)
	@ExcelProperty("姓名")
	private String name;
	
	@Column(length = 50)
	@ExcelProperty("值")
	private String value;
	
	@Column(length = 50)
	@ExcelProperty("CODE")
	private String code;
	
	@Column(length = 50)
	@ExcelProperty("ID")
	private String info;
	
	@Column(name="create_time")
	@ExcelProperty("创建时间")
	private Date createTime;
	
	
	@Column(name="update_time")
	@ExcelProperty("更新时间")
	private Date updateTime;
	
	@Column(name="opt_user")
	@ExcelProperty("操作者")
	private String optUser;
	
	@Column(name="info_desc")
	@ExcelProperty("描述")
	private String desc;
	
	@ExcelProperty("备用字段1")
	private String col1;
	
	@ExcelProperty("备用字段2")
	private String col2;

}
