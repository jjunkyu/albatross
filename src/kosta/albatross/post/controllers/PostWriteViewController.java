package kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberVO;

public class PostWriteViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if(session == null || memberVO == null) {
			return "index.jsp";
		}
		String url = "/post/postWrite.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "post-write");
		return TEMPLATE_PATH + "home.jsp";
	}

}
