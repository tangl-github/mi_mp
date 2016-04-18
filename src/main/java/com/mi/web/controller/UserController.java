package com.mi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制层
 * @author tangl
 * 2016-2-16 16:59:09
 */
@Controller
@RequestMapping("")
public class UserController extends BaseController{

	@RequestMapping(value = "login")
	public String login() {
		return "login";
	}
}
