package kosta.albatross.common.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivacyPolicyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/topic/privacyPolicy.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "privacy-policy");
		return TEMPLATE_PATH + "home.jsp";
	}

}
