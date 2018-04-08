package com.kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.post.models.PostDAO;
import com.kosta.albatross.post.models.PostVO;

public class PostUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("loginVO")==null) {
			return "index.jsp";
		}else {
			int pNo =Integer.parseInt(request.getParameter("pNo"));
			String pTitle = request.getParameter("title");
			String content = request.getParameter("content");
			PostVO pvo = PostDAO.getInstance().getPostUpdate(
					new PostVO(pNo,pTitle,content,0,null,null) );
			return REDIRECT_PREFIX + "dispatcher?command=postDetail&pNo=" + pvo.getpNo();
		}
	}
}
