package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostWriteViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/post/postWrite.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "postWrite");
		return TEMPLATE_PATH + "home.jsp";
	}

}
