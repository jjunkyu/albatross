package kosta.albatross.common.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String url = "main.jsp";
		request.setAttribute("url",url);
		request.setAttribute("page", "home");
		return TEMPLATE_PATH + "home.jsp";
	}

}
