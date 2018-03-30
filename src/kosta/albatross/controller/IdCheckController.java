package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.MemberDAO;


public class IdCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");				
		boolean flag=MemberDAO.getInstance().idcheck(id);		
		if(flag)
			return TEMPLATE_PATH + "idcheck_fail.jsp";
		else
			return TEMPLATE_PATH + "idcheck_ok.jsp";
	}

}
