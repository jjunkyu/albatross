package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return REDIRECT_PREFIX + TEMPLATE_PATH + "home.jsp";
	}

}
