package com.comviva.reconciliation.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController implements ErrorController {
	
	private static final String PATH="/error";
	
	@RequestMapping("/")
	public String home() {
		return "html/embed.html";
	}
	
	@RequestMapping("/index")
	public String login() {
		return "html/index.jsp";
	}
	@RequestMapping("/report2")
	public String report2() {
		return "html/report2.html";
	}
	
	@RequestMapping("logout-success")
	public String logout() {
		return "html/index.jsp";
	}
	
	@RequestMapping(value=PATH)
	public String error() {
		return "ERROR";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}
