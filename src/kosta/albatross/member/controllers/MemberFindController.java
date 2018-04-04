package kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;
import kosta.albatross.member.models.MemberVO;

public class MemberFindController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO mvo = new MemberVO();
		String answer = request.getParameter("question_answer");
		String email = request.getParameter("email");
		String qId = request.getParameter("question");
		mvo = MemberDAO.getInstance().getMemberFind(email, answer, qId);
		String url = "/member/memberFind_ok.jsp";
		request.setAttribute("mvo", mvo);
		request.setAttribute("url", url);
		request.setAttribute("page", "member-find");
		return TEMPLATE_PATH + "home.jsp";
	}

}
