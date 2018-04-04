package kosta.albatross.rent.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberVO;
import kosta.albatross.rent.models.RentDAO;
import kosta.albatross.rent.models.RentVO;

public class RentListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if(session==null || memberVO == null) {
			return "index.jsp";
		}else {
			ArrayList<RentVO> rentList = RentDAO.getInstance().rentList(memberVO.getId());
			String url = "/rent/rentList.jsp";
			request.setAttribute("rentList", rentList);
			request.setAttribute("url",url);
			request.setAttribute("page", "rent-list");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
