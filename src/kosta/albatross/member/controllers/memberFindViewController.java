package kosta.albatross.member.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberDAO;

public class memberFindViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String>list = new ArrayList<String>();
		MemberDAO memberDAO =null;
		list=memberDAO.getInstance().questionList();

		String url = "/member/memberFindView.jsp";
		request.setAttribute("list", list);
		request.setAttribute("url", url);
		request.setAttribute("page", "member-Find-View");
		return TEMPLATE_PATH + "home.jsp";
	}
}
