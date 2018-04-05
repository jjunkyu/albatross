package kosta.albatross.rent.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.common.controllers.Controller;
import kosta.albatross.member.models.MemberVO;
import kosta.albatross.rent.models.RentDAO;

public class ReturnBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if(session == null || memberVO == null) {
			return REDIRECT_PREFIX +"index.jsp";
		}else {
			int bNo = Integer.parseInt(request.getParameter("bNo"));
			String isRented = request.getParameter("isRented");
			RentDAO.getInstance().returnRentBook(memberVO.getId(), bNo);
			BookDAO.getInstance().changeOfRented(bNo,isRented);
			return REDIRECT_PREFIX + "dispatcher?command=rentList";
		}
	}
}
