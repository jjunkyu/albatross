package com.kosta.albatross.book.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.albatross.Controller;
import com.kosta.albatross.ListVO;
import com.kosta.albatross.PagingBean;
import com.kosta.albatross.book.models.BookDAO;
import com.kosta.albatross.book.models.BookVO;

public class BookSearchByTitleController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PagingBean pagingBean = null;
		String pNo = request.getParameter("pNo");
		String searchStr = request.getParameter("value");
		int totalCount = BookDAO.getInstance().searchByTitleCount(searchStr);
		if (pNo == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pNo));
		}
		ArrayList<BookVO> list = BookDAO.getInstance().searchByTitle(searchStr, pagingBean);
		ListVO listVO = new ListVO();
		if(list==null)
			return "/book/bookSearch_fail.jsp";
		String url = "/book/bookSearch_ok.jsp";
		listVO.setBookList(list);
		listVO.setPagingBean(pagingBean);
		request.setAttribute("command","bookSearchByTitle");
		request.setAttribute("value", searchStr);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "bookSearch-ok");
		return TEMPLATE_PATH + "home.jsp";
	}
}
