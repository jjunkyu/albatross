package com.kosta.albatross.book.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.albatross.Controller;
import com.kosta.albatross.ListVO;
import com.kosta.albatross.PagingBean;
import com.kosta.albatross.book.models.BookDAO;
import com.kosta.albatross.book.models.BookVO;

public class BookListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PagingBean pagingBean = null;
		int totalCount = BookDAO.getInstance().getTotalBookCount();
		String pNo = request.getParameter("pNo");
		if (pNo == null) {
			pagingBean = new PagingBean(totalCount);
		}else {
			pagingBean = new PagingBean(totalCount,Integer.parseInt(pNo));
		}
		ArrayList<BookVO> bookList = BookDAO.getInstance().getBookList(pagingBean);
		ListVO listVO = new ListVO();
		String url = "/book/bookList.jsp";
		listVO.setBookList(bookList);
		listVO.setPagingBean(pagingBean);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "book-list");
		return TEMPLATE_PATH + "home.jsp";
	}
}
