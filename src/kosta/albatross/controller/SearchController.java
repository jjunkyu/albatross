package kosta.albatross.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.BookDAO;
import kosta.albatross.dao.PagingBean;
import kosta.albatross.vo.BookVO;
import kosta.albatross.vo.ListVO;

public class SearchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String searchBy = request.getParameter("by");
		String searchStr = request.getParameter("value");
		BookDAO dao = BookDAO.getInstance();
		ArrayList<BookVO> list = null;
		ListVO listVO = new ListVO();
		if(searchBy.equals("author")) {
			list = dao.searchByAuthor(searchStr);
		} else if(searchBy.equals("title")) {
			list = dao.searchByTitle(searchStr);
		} else if(searchBy.equals("mixed")) {
			list = dao.searchByTitleAndAuthor(searchStr);
		}
		String url = "/book/bookListView.jsp";
		listVO.setBookList(list);
		listVO.setPagingBean(new PagingBean(dao.getTotalBookCount()));
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "search-result");
		return TEMPLATE_PATH + "home.jsp";
	}
}
