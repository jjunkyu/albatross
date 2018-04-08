package com.kosta.albatross;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.albatross.book.models.BookDAO;
import com.kosta.albatross.book.models.BookVO;

public class HomeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String url = "main.jsp";
		ArrayList<BookVO> top5books = BookDAO.getInstance().getTop5Books();
		request.setAttribute("url",url);
		request.setAttribute("books", top5books);
		request.setAttribute("page", "home");
		return TEMPLATE_PATH + "home.jsp";
	}

}
