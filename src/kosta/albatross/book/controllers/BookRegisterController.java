package kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.controllers.Controller;

public class BookRegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bNo = request.getParameter("bNo");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		BookVO vo = new BookVO(Integer.parseInt(bNo),title,content,author,publisher);
		BookDAO.getInstance().bookRegister(vo);
		String url = "/book/bookRegister.jsp";
		request.setAttribute("url", url);
		request.setAttribute("page", "library-page");
		return TEMPLATE_PATH + "home.jsp";
	}
}
