package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.BookDAO;
import kosta.albatross.dao.RentDAO;
import kosta.albatross.vo.MemberVO;

public class ReturnBookController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if(session == null || memberVO == null) {
			return "index.jsp";
		}else {
			int bNo = Integer.parseInt(request.getParameter("bNo"));
			String isRented = request.getParameter("isRented");
			RentDAO.getInstance().deleteRentItem(bNo);
			BookDAO.getInstance().changeOfRented(bNo,isRented);
			return REDIRECT_PREFIX + "index.jsp";
		}
	}
}
