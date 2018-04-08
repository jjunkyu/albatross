package com.kosta.albatross.member.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.albatross.Controller;
import com.kosta.albatross.member.models.MemberDAO;
import com.kosta.albatross.member.models.MemberVO;

public class RegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String answer=request.getParameter("answer");
		String qid=request.getParameter("qid");
		
		MemberVO memberVO=new MemberVO(id,password,name,address,null,email,answer,qid);	
		MemberDAO.getInstance().register(memberVO);
		return REDIRECT_PREFIX + "dispatcher?command=login";
	}
}
