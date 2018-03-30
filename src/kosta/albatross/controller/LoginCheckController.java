package kosta.albatross.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.dao.MemberDAO;
import kosta.albatross.vo.MemberVO;

public class LoginCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("userID");
		String password = request.getParameter("userPassword");
		MemberVO memberVO = MemberDAO.getInstance().login(id, password);
		HttpSession session = request.getSession();
		if (memberVO != null) {

			session.setAttribute("loginVO", memberVO);
			//조회수 조회를 위해 배열 선언
			session.setAttribute("pNoList", new ArrayList<Integer>());

			return REDIRECT_PREFIX + "index.jsp";
		}else { 
			String logincheck=null;
			session.setAttribute("failLogin", logincheck);
			request.setAttribute("url", "login.jsp");
			request.setAttribute("page", "login-check");
			return TEMPLATE_PATH + "home.jsp";
		}
	}

}
