package kosta.albatross.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("loginVO") != null) {
			return REDIRECT_PREFIX + "index.jsp";
		} else {
			String url = "/member/login.jsp";
			session.setAttribute("failLogin", "true");
			request.setAttribute("url", url);
			request.setAttribute("page", "login");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
