package com.kosta.albatross;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String REDIRECT_PREFIX = "redirect:";
	String TEMPLATE_PATH = "jsp/templates/";
	String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
