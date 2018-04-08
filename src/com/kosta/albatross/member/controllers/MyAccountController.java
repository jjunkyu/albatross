package com.kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.member.models.MemberVO;

public class MyAccountController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if(session == null || memberVO == null) {
			return REDIRECT_PREFIX +"index.jsp";
		}else {
		String url = "/member/myAccount.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "my-account");
		return TEMPLATE_PATH + "home.jsp";
		}
	}

}
