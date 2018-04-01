package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.PostDAO;
import kosta.albatross.vo.PostVO;

public class postUpdateViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginVO") == null) {
			return "REDIRECT_PREFIX+index.jsp";
		}
		int pNo=Integer.parseInt(request.getParameter("pNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		PostVO pvo = PostDAO.getInstance().getPostUpdate(pNo,title,content);

		request.setAttribute("pvo", pvo);
		request.setAttribute("url", "detailPost.jsp");
		request.setAttribute("page", "post-update");
		return TEMPLATE_PATH + "home.jsp";		
	}

}
