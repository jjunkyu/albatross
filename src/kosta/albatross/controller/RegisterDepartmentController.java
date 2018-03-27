package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.DepartmentDAO;

public class RegisterDepartmentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String dno = request.getParameter("dno");
		String dname = request.getParameter("name");
		String loc = request.getParameter("loc");
		String tel = request.getParameter("tel");
		DepartmentDAO.getInstance().registerDepartment(dno, dname, loc, tel);
		return "redirect:register";
	}
}
