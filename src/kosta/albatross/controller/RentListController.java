package kosta.albatross.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.RentDAO;
import kosta.albatross.vo.BookVO;
import kosta.albatross.vo.MemberVO;

public class RentListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if(session==null || session.getAttribute("loginVO")==null) {
			return "index.jsp";
		}else {
			ArrayList<BookVO> rentList =
					RentDAO.getInstance().rentList(memberVO.getId());
			String url = "rentList.jsp";
			request.setAttribute("rentList", rentList);
			request.setAttribute("url",url);
			request.setAttribute("page", "rentList");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
