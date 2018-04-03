package kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;

public class BookRegisterViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/book/bookRegister.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "library-page");
		return TEMPLATE_PATH + "home.jsp";
	}
}
