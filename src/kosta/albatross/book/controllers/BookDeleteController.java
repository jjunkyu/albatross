package kosta.albatross.book.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.book.models.BookDAO;
import kosta.albatross.common.controllers.Controller;

public class BookDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("loginVO")==null||
				request.getMethod().equals("POST")==false){
			return REDIRECT_PREFIX + "index.jsp";
		}
		String bNo=request.getParameter("bNo");
		BookDAO.getInstance().deleteBook(Integer.parseInt(bNo));
		
		return REDIRECT_PREFIX + "dispatcher?command=bookList";
	}

}
