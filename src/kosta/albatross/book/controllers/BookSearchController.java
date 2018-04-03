package kosta.albatross.book.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.controllers.Controller;
import kosta.albatross.common.models.ListVO;
import kosta.albatross.common.models.PagingBean;

public class BookSearchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String searchBy = request.getParameter("by");
		String searchStr = request.getParameter("value");
		BookDAO dao = BookDAO.getInstance();
		ArrayList<BookVO> list = null;
		PagingBean pagingBean = null;
		ListVO listVO = new ListVO();
		if(searchBy.equals("author")) {
			list = dao.searchByAuthor(searchStr);
		} else if(searchBy.equals("title")) {
			list = dao.searchByTitle(searchStr);
		} else if(searchBy.equals("mixed")) {
			list = dao.searchByTitleAndAuthor(searchStr);
		}
		if(list==null) {
			return "index.jsp";
		}else {
			int totalCount = list.size();
			String bNo = request.getParameter("bNo");
			if (bNo == null) {
				pagingBean = new PagingBean(totalCount);
			}else {
				pagingBean = new PagingBean(totalCount,Integer.parseInt(bNo));
			}
			String url = "/book/bookList.jsp";
			listVO.setBookList(list);
			listVO.setPagingBean(pagingBean);
			request.setAttribute("listVO", listVO);
			request.setAttribute("url", url);
			request.setAttribute("page", "search-result");
			return TEMPLATE_PATH + "home.jsp";
		}
	}
}
