package kosta.albatross.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.albatross.dao.PagingBean;
import kosta.albatross.dao.PostDAO;
import kosta.albatross.vo.ListVO;
import kosta.albatross.vo.PostVO;

public class PostListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalCount = PostDAO.getInstance().getTotalPostCount();
		String pno = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if (pno == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pno));
		}
		ArrayList<PostVO> list = PostDAO.getInstance().getPostList(pagingBean);
		ListVO listVO = new ListVO(list, pagingBean);
		request.setAttribute("list", list);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", "postList.jsp");
		request.setAttribute("page", "post-page");
		return TEMPLATE_PATH + "home.jsp";
	}
}
