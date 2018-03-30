package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.MemberDAO;
import kosta.albatross.vo.MemberVO;

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
		
		request.setAttribute("url", "login.jsp");
		request.setAttribute("page", "login");
		return TEMPLATE_PATH + "home.jsp";
	}

}
