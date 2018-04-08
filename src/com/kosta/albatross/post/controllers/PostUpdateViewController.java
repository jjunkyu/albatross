package com.kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.post.models.PostDAO;
import com.kosta.albatross.post.models.PostVO;

public class PostUpdateViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginVO") == null) {
			return "index.jsp";
		}
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		PostVO PostVO = PostDAO.getInstance().getPostDetail(pNo);
		String url = "../post/postUpdate.jsp";
		request.setAttribute("PostVO", PostVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "post-update");
		return TEMPLATE_PATH + "home.jsp";		
	}

}
