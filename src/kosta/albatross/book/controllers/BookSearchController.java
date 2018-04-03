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
		String pNo = request.getParameter("pNo");
		BookDAO dao = BookDAO.getInstance();
		ArrayList<BookVO> list = null;
		PagingBean pagingBean = null;
		ListVO listVO = new ListVO();
		int totalCount = 0;
		if(searchBy != null && searchStr != null) {
			if (searchBy.equals("author")) {
				totalCount = dao.searchByAuthorCount(searchStr);
				if (pNo == null) {
					pagingBean = new PagingBean(totalCount);
				} else {
					pagingBean = new PagingBean(totalCount, Integer.parseInt(pNo));
				}
				list = dao.searchByAuthor(searchStr, pagingBean);
			} else if (searchBy.equals("title")) {
				totalCount = dao.searchByTitleCount(searchStr);
				if (pNo == null) {
					pagingBean = new PagingBean(totalCount);
				} else {
					pagingBean = new PagingBean(totalCount, Integer.parseInt(pNo));
				}
				list = dao.searchByTitle(searchStr, pagingBean);
			} else if (searchBy.equals("mixed")) {
				totalCount = dao.searchByTitleAndAuthorCount(searchStr);
				if (pNo == null) {
					pagingBean = new PagingBean(totalCount);
				} else {
					pagingBean = new PagingBean(totalCount, Integer.parseInt(pNo));
				}
				list = dao.searchByTitleAndAuthor(searchStr, pagingBean);
			}
		}
		if(list==null)
			return "/book/bookSearch_fail.jsp";
		String url = "/book/bookSearch_ok.jsp";
		listVO.setBookList(list);
		listVO.setPagingBean(pagingBean);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "bookSearch-ok");
		return TEMPLATE_PATH + "home.jsp";
	}
}
