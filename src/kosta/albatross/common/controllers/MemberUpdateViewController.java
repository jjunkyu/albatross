package kosta.albatross.common.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.member.models.MemberDAO;
import kosta.albatross.member.models.MemberVO;

public class MemberUpdateViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginVO")==null) {
			return REDIRECT_PREFIX + "index.jsp";
		}else {
			MemberVO memberVO = (MemberVO)session.getAttribute("loginVO");
			String query = MemberDAO.getInstance().questionQuery(memberVO.getqId());
			request.setAttribute("pidQuery", query);
			request.setAttribute("url", "/member/memberUpdate.jsp");
			request.setAttribute("page", "memberUpdate");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
