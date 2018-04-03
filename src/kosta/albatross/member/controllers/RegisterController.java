package kosta.albatross.member.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;
import kosta.albatross.member.models.MemberVO;

public class RegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String answer=request.getParameter("anwer");
		String qid=request.getParameter("qid");
		System.out.println(qid);
		
		MemberVO memberVO=new MemberVO(id,password,name,address,null,email,answer,qid);	
		System.out.println(memberVO.getqId());
		MemberDAO.getInstance().register(memberVO);
		String url = "/member/login.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "login");
		return TEMPLATE_PATH + "home.jsp";
	}

}
