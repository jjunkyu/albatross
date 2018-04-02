package kosta.albatross.book.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.controllers.Controller;
import kosta.albatross.common.models.ListVO;
import kosta.albatross.common.models.PagingBean;

public class BookListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PagingBean pagingBean = null;
		int totalCount = BookDAO.getInstance().getTotalBookCount();
		String bNo = request.getParameter("bNo");
		if (bNo == null) {
			pagingBean = new PagingBean(totalCount);
		}else {
			pagingBean = new PagingBean(totalCount,Integer.parseInt(bNo));
		}
		ArrayList<BookVO> bookList = BookDAO.getInstance().getBookList(pagingBean);
		ListVO listVO = new ListVO();
		String url = "/book/bookList.jsp";
		listVO.setBookList(bookList);
		listVO.setPagingBean(pagingBean);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "library-page");
		return TEMPLATE_PATH + "home.jsp";
	}
}
