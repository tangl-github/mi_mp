package com.mi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(value = "home")
	public String execute() {
		System.out.println("home");
		return "index";
	}
}
