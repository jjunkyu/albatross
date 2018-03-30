package kosta.albatross.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.PostDAO;
import kosta.albatross.vo.PostVO;

public class showDetailContentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("loginVO")==null){
			return REDIRECT_PREFIX + "index.jsp";
		}
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer> pNoList = (ArrayList<Integer>) session.getAttribute("pNoList");
		if(pNoList.contains(pNo) == false) {
			PostDAO.getInstance().updateHit(pNo);
			pNoList.add(pNo);
		}
		
		PostVO vo = PostDAO.getInstance().getPostByNo(pNo);
		request.setAttribute("pvo", vo);
		request.setAttribute("url", "detailPost.jsp");
		request.setAttribute("page", "detail-post-page");
		return TEMPLATE_PATH + "home.jsp";
	}

}
