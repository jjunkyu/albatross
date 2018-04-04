package kosta.albatross.book.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.common.controllers.Controller;
import kosta.albatross.exception.NotDeleteBookException;

public class BookDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginVO") == null) {
			return REDIRECT_PREFIX + "index.jsp";
		}
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		try {
			BookDAO.getInstance().deleteBook(bNo);
		} catch (NotDeleteBookException e) {
			return "book/bookDelete_fail.jsp";
		}
		return REDIRECT_PREFIX + "dispatcher?command=bookList";
	}

}
