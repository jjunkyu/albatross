package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.BookDAO;
import kosta.albatross.vo.BookVO;

public class BookDetail_jjsController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		BookVO bookVO = BookDAO.getInstance().getBookDetail(bNo);
		request.setAttribute("bookVO",bookVO);
		request.setAttribute("url", "bookDetail_jjs.jsp");
		request.setAttribute("page", "bookDetail");
		return TEMPLATE_PATH + "home.jsp";
	}

}
