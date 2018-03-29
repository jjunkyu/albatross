package kosta.albatross.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.BookDAO;
import kosta.albatross.dao.PagingBean;
import kosta.albatross.vo.BookVO;
import kosta.albatross.vo.ListVO;

public class BookListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PagingBean pagingBean = null;
		int totalCount = BookDAO.getInstance().getTotalPostCount();
		String bNo = request.getParameter("bNo");
		if (bNo == null) {
			pagingBean = new PagingBean(totalCount);
		}else {
			pagingBean = new PagingBean(totalCount,Integer.parseInt(bNo));
		}
		ArrayList<BookVO> bookList = BookDAO.getInstance().getBookList(pagingBean);
		ListVO listVO = new ListVO();
		listVO.setBookList(bookList);
		listVO.setPagingBean(pagingBean);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", "bookListView.jsp");
		request.setAttribute("page", "library-page");
		return TEMPLATE_PATH + "home.jsp";
	}
}
