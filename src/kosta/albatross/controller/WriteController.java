package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.PostDAO;
import kosta.albatross.vo.MemberVO;
import kosta.albatross.vo.PostVO;

public class WriteController implements Controller {

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
			
			request.setAttribute("pvo", postVO);
			request.setAttribute("url", "detailPost.jsp");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
