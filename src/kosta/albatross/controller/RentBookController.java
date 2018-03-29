package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.RentDAO;
import kosta.albatross.vo.MemberVO;

public class RentBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if(session==null||session.getAttribute("memberVO")==null) {
			return "index.jsp";
		}else {
			int bNo = Integer.parseInt(request.getParameter("bNo"));
			RentDAO.getInstance().addRentItem(memberVO.getId(), bNo);
			request.setAttribute("url", "rent_ok.jsp");
			request.setAttribute("page", "rent_ok-page");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
