package com.primetgi.org.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/test1")
	public String showTestPage() {
		return "test-page";
	}
}
