package kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;
import kosta.albatross.member.models.MemberVO;

public class MemberUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginVO")==null) {
			return REDIRECT_PREFIX + "index.jsp";
		}else {
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String answer = request.getParameter("answer");
			String id = request.getParameter("id");
			
			MemberVO memberVO = MemberDAO.getInstance().memberUpdate(password, name, address, answer, id);
			String query = MemberDAO.getInstance().questionQuery(memberVO.getqId());
			request.setAttribute("pidQuery", query);
			session.setAttribute("loginVO", memberVO);	
			request.setAttribute("url", "/member/memberUpdate.jsp");
			request.setAttribute("page", "member-update");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
