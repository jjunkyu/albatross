package kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.controllers.Controller;

public class BookRegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginVO") == null) {
			return REDIRECT_PREFIX + "index.jsp";
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		BookVO vo = new BookVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setAuthor(author);
		vo.setPublisher(publisher);
		BookDAO.getInstance().bookRegister(vo);
		return REDIRECT_PREFIX + "book/bookRegister_ok.jsp";
	}
}
