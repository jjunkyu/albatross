package com.kosta.albatross.post.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.albatross.Controller;
import com.kosta.albatross.ListVO;
import com.kosta.albatross.PagingBean;
import com.kosta.albatross.post.models.PostDAO;
import com.kosta.albatross.post.models.PostVO;

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
		String url = "../post/postList.jsp";
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "post-page");
		return TEMPLATE_PATH + "home.jsp";
	}
}
