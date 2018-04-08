package com.kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.book.models.BookDAO;
import com.kosta.albatross.book.models.BookVO;
import com.kosta.albatross.member.models.MemberVO;

public class BookDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if(session == null || memberVO == null) {
			return "index.jsp";
		}
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		BookVO bookVO = BookDAO.getInstance().getBookDetail(bNo);
		String url = "../book/bookDetail.jsp";
		request.setAttribute("bookVO",bookVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "bookDetail");
		return TEMPLATE_PATH + "home.jsp";
	}

}
