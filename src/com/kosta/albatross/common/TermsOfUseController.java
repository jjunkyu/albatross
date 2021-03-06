package com.kosta.albatross.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.albatross.Controller;

public class TermsOfUseController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "../topic/termsOfUse.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "terms-of-use");
		return TEMPLATE_PATH + "home.jsp";
	}

}
