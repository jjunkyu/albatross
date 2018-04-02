package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.BookDAO;
import kosta.albatross.vo.BookVO;

public class BookDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		BookVO bookVO = BookDAO.getInstance().getBookDetail(bNo);
		String url = "/book/bookDetail.jsp";
		request.setAttribute("bookVO",bookVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "bookDetail");
		return TEMPLATE_PATH + "home.jsp";
	}

}
