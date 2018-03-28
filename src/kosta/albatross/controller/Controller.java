package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String REDIRECT_PREFIX = "redirect:";
	String TEMPLATE_PATH = "templates/";
	String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
