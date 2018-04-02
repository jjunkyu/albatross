package kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberVO;
import kosta.albatross.post.models.PostDAO;
import kosta.albatross.post.models.PostVO;

public class PostWriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("loginVO") == null) {
			return "index.jsp";
		}else {
			MemberVO memberVO = (MemberVO)session.getAttribute("loginVO");
			String id = memberVO.getId();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			PostVO postVO = PostDAO.getInstance().writeContent(id, title, content);
			String url = "/post/postDetail.jsp";
			request.setAttribute("pvo", postVO);
			request.setAttribute("url", url);
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
