package kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;
import kosta.albatross.member.models.MemberVO;

public class memberFindController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO memberDAO =null;
		MemberVO mvo = new MemberVO();
		String answer = request.getParameter("question_answer");
		String email = request.getParameter("email");
		String qid = memberDAO.getInstance().getMemberFindQid(email);
		mvo= memberDAO.getInstance().getMemberFind(email,answer,qid);
		String url = "/member/memberFind_ok.jsp";
		request.setAttribute("mvo", mvo);
		request.setAttribute("url", url);
		request.setAttribute("page", "member-find");
		return TEMPLATE_PATH + "home.jsp";
	}

}
