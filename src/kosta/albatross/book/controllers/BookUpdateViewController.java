package kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.controllers.Controller;

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
