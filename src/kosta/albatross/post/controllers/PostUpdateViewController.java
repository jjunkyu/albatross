package kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.post.models.PostDAO;
import kosta.albatross.post.models.PostVO;

public class PostUpdateViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginVO") == null) {
			return "REDIRECT_PREFIX+index.jsp";
		}
		PostVO postVO = new PostVO();
		postVO.setpNo(Integer.parseInt(request.getParameter("pNo")));
		postVO.setTitle(request.getParameter("title"));
		postVO.setContent(request.getParameter("content"));
		String url = "/post/postDetail.jsp";
		PostVO pvo = PostDAO.getInstance().getPostUpdate(postVO);
		request.setAttribute("pvo", pvo);
		request.setAttribute("url", url);
		request.setAttribute("page", "post-update");
		return TEMPLATE_PATH + "home.jsp";		
	}

}
