package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.vo.PostVO;

public class postUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("loginVO")==null) {
			return "index.jsp";
		}else {
			int pNo =Integer.parseInt(request.getParameter("pNo"));
			String pTitle = request.getParameter("title");
			String content = request.getParameter("content");
			PostVO pvo = new PostVO(pNo,pTitle,content,0,null,null);
			request.setAttribute("PostVO", pvo);
			request.setAttribute("url","postUpdate.jsp");
			request.setAttribute("page", "post-update-page");
			return TEMPLATE_PATH + "home.jsp";
		}
	}
}
