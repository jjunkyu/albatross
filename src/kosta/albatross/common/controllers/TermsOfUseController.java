package kosta.albatross.common.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TermsOfUseController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/topic/termsOfUse.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "terms-of-use");
		return TEMPLATE_PATH + "home.jsp";
	}

}
