package kosta.albatross.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.BookDAO;
import kosta.albatross.vo.BookVO;

public class SearchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String searchBy = request.getParameter("by");
		String searchStr = request.getParameter("value");
		BookDAO dao = BookDAO.getInstance();
		ArrayList<BookVO> list = null;
		if(searchBy.equals("author")) {
			list = dao.searchByAuthor(searchStr);
		} else if(searchBy.equals("title")) {
			list = dao.searchByTitle(searchStr);
		} else if(searchBy.equals("mixed")) {
			
		}
		request.setAttribute("list", list);
		request.setAttribute("url", "bookListView.jsp");
		request.setAttribute("page", "search-result");
		return TEMPLATE_PATH + "home.jsp";
	}

}
