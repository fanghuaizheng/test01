package com.example.demo.controller;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.example.demo.entity.InfoDemo;
import com.example.demo.service.InfoService;
import com.example.demo.utils.AjaxResult;
import com.example.demo.vo.ExportInfoVo;
import com.example.demo.vo.ObjectField;

@RestController
@RequestMapping("info")
public class InfoController {
	
	
	@Autowired
	InfoService infoService;
	
	/**
	 * 查询分页数据
	 * @param eg
	 * @param pageSize 
	 * @param pageNum
	 * @return
	 */
	@GetMapping("pageData")
	public Object pgaeData(InfoDemo eg,
			@RequestParam(value="pageSize",defaultValue = "10") int pageSize,
			@RequestParam(name="pageNum",defaultValue = "1")int pageNum) {
		
		Pageable page  = PageRequest.of(pageNum-1, pageSize);
		
		Page data = infoService.findAllByParam(eg, page);
		
		return AjaxResult.success(data);
	}
	
	@GetMapping("download")
	public void downloadTemplate(HttpServletResponse response,ExportInfoVo eg) throws Exception {
		
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		response.setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode("测试", "UTF-8") + ".xlsx");
		InfoDemo demo = new InfoDemo();
		BeanUtils.copyProperties(eg, demo);
		
		List<InfoDemo> data = infoService.findAll(demo);
		List<String> fields = Arrays.asList(eg.getFields().split(","));
		EasyExcel.write(response.getOutputStream(),InfoDemo.class)
//		.head(head())
		.includeColumnFiledNames(fields)
		.includeColumnFiledNames(fields)
		.autoTrim(true)
		.sheet().doWrite(data);
		


		
	}
	
	

	
	@GetMapping("getAllFields")
	public AjaxResult getAllFields() {
		List<ObjectField> result = new ArrayList<>();
		getAllFields(InfoDemo.class).forEach(x->{
			ObjectField item = new ObjectField();
			String fieldName = x.getName();
			item.setName(fieldName);
			ExcelProperty anno = x.getAnnotation(ExcelProperty.class);
			if(anno==null) {
				
				item.setValue(fieldName);
				
			}else {
				String[] value = anno.value();
				if(value==null) {
					item.setValue(fieldName);
				}else {
					item.setValue(StringUtils.join(value, ","));
				}
			}
			result.add(item);
		});
		return AjaxResult.success(result);
		
	}
	
	
	private  List<Field> getAllFields(Class<?> clazz){
		  List<Field> fieldList = new ArrayList<>();
		  while (clazz != null){
		    fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
		    clazz = clazz.getSuperclass();
		  }
		  return fieldList;
	}
	
	@GetMapping("test")
	public Object test() {
		return "1";
	}

}
