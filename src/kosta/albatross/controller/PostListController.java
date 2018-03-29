package kosta.albatross.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.PostDAO;
import kosta.albatross.vo.PostVO;

public class PostListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		ArrayList<PostVO> list = PostDAO.getInstance().getPostList();
		request.setAttribute("list", list);
		request.setAttribute("url", "postList.jsp");
		request.setAttribute("page", "post-page");
		return TEMPLATE_PATH + "home.jsp";
	}
}
