package kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.controllers.Controller;

public class BookUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("loginVO")==null) {
			return "index.jsp";
		}else {
			int bNo =Integer.parseInt(request.getParameter("bNo"));
			String content = request.getParameter("content");
			BookVO bookVO = new BookVO();
			bookVO.setbNo(bNo);
			bookVO.setContent(content);
			BookDAO.getInstance().bookUpdate(bookVO);
			return REDIRECT_PREFIX + "dispatcher?command=bookList";
		}
	}

}
