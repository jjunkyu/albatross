package kosta.albatross.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.PostDAO;

public class deletePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("loginVO")==null||
				request.getMethod().equals("POST")==false){
			return REDIRECT_PREFIX + "index.jsp";
		}
		String pNo=request.getParameter("pNo");
		PostDAO.getInstance().deletePosting(Integer.parseInt(pNo));
		
		return REDIRECT_PREFIX + "dispatcher?command=postList";
	}

}
