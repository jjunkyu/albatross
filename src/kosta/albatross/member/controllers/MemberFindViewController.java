package kosta.albatross.member.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;

public class MemberFindViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String>list = new ArrayList<String>();
		list=MemberDAO.getInstance().questionList();
		String url = "/member/memberFindView.jsp";
		request.setAttribute("list", list);
		request.setAttribute("url", url);
		request.setAttribute("page", "member-find-view");
		return TEMPLATE_PATH + "home.jsp";
	}
}