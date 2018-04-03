package kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.controllers.Controller;
import kosta.albatross.post.models.PostDAO;

public class PostDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("loginVO")==null){
			return REDIRECT_PREFIX + "index.jsp";
		}
		String pNo=request.getParameter("pNo");
		PostDAO.getInstance().deletePosting(Integer.parseInt(pNo));
		
		return REDIRECT_PREFIX + "dispatcher?command=postList";
	}

}
