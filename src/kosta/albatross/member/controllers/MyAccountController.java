package kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;

public class MyAccountController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/myAccount.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "my-account");
		return TEMPLATE_PATH + "home.jsp";
	}

}
