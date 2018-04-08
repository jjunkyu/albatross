package com.kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.book.models.BookDAO;
import com.kosta.albatross.book.models.BookVO;

public class BookUpdateViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginVO") == null) {
			return REDIRECT_PREFIX + "index.jsp";
		}
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		BookVO bookVO = BookDAO.getInstance().getBookDetail(bNo);
		String url="/book/bookUpdate.jsp";
		request.setAttribute("url", url);
		request.setAttribute("bookVO", bookVO);
		request.setAttribute("page", "book-update-page");
		return TEMPLATE_PATH + "home.jsp";
	}

}
