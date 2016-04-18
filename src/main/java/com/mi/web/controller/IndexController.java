package com.mi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mi.service.IIndexService;
/**
 * 主页控制层
 * @author tangl
 * 2016-2-16 16:59:09
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	@Autowired
	private IIndexService service;

	@RequestMapping(value = "home")
	public String execute() {
		log.info("进入主页");
		return "index";
	}
}
