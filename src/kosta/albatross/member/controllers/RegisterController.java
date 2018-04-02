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
		//String id, String password, String address, String name, String cId
		MemberVO memberVO=new MemberVO(id,password,address,name,null);	
		MemberDAO.getInstance().register(memberVO);
		String url = "/member/login.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "login");
		return TEMPLATE_PATH + "home.jsp";
	}

}
