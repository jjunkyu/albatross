package kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;

public class IdCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");				
		boolean flag=MemberDAO.getInstance().idCheck(id);		
		if(flag)
			return "member/idcheck_fail.jsp";
		else
			return "member/idcheck_ok.jsp";
	}

}