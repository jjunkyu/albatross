package kosta.albatross.member.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;

public class RegisterViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/register.jsp";
		ArrayList<String> questionList = MemberDAO.getInstance().questionList();
		request.setAttribute("questionList", questionList);
		request.setAttribute("url", url);
		request.setAttribute("page", "register-view");
		return TEMPLATE_PATH + "home.jsp";
	}

}
