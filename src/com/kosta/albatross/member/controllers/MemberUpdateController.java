package com.kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.member.models.MemberDAO;
import com.kosta.albatross.member.models.MemberVO;

public class MemberUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginVO")==null) {
			return "index.jsp";
		}else {
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String answer = request.getParameter("answer");
			String id = request.getParameter("id");
			
			MemberVO memberVO = MemberDAO.getInstance().memberUpdate(password, name, address, answer, id);
			session.setAttribute("loginVO", memberVO);	
			return REDIRECT_PREFIX + "index.jsp";
		}
	}
}
