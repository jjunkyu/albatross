package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/register.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "registerView");
		return TEMPLATE_PATH + "home.jsp";
	}

}
