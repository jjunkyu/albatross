package kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.post.models.PostVO;

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
			String url = "/post/postUpdate.jsp";
			PostVO pvo = new PostVO(pNo,pTitle,content,0,null,null);
			request.setAttribute("PostVO", pvo);
			request.setAttribute("url", url);
			request.setAttribute("page", "post-update-page");
			return TEMPLATE_PATH + "home.jsp";
		}
	}
}
