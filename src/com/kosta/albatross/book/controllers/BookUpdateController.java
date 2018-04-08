package com.kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.book.models.BookDAO;
import com.kosta.albatross.book.models.BookVO;

public class BookUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session==null || session.getAttribute("loginVO")==null) {
			return "index.jsp";
		}else {
			int bNo =Integer.parseInt(request.getParameter("bNo"));
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String publisher = request.getParameter("publisher");
			String content = request.getParameter("content");
			BookVO bookVO = new BookVO();
			bookVO.setbNo(bNo);
			bookVO.setTitle(title);
			bookVO.setContent(content);
			bookVO.setAuthor(author);
			bookVO.setPublisher(publisher);
			BookDAO.getInstance().bookUpdate(bookVO);
			return REDIRECT_PREFIX + "dispatcher?command=bookDetail&bNo=" + bNo;
		}
	}

}
