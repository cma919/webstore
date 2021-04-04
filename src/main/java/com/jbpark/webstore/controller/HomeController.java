package com.jbpark.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("greeting", "ȯ���մϴ�!");
		model.addAttribute("tagline", "���󿡼� �ϳ� ���� �� ����");
		return "welcome";
	}
}