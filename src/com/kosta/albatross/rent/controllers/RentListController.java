package com.kosta.albatross.rent.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.ListVO;
import com.kosta.albatross.PagingBean;
import com.kosta.albatross.member.models.MemberVO;
import com.kosta.albatross.rent.models.RentDAO;
import com.kosta.albatross.rent.models.RentVO;

public class RentListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		ListVO listVO = new ListVO();
		if (session == null || memberVO == null) {
			return REDIRECT_PREFIX +"index.jsp";
		}
		PagingBean pagingBean = null;
		int totalCount = RentDAO.getInstance().getTotalRentCount(memberVO.getId());
		String pNo = request.getParameter("pNo");
		if (pNo == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pNo));
		}
		ArrayList<RentVO> list = RentDAO.getInstance().rentList(memberVO.getId(), pagingBean);
		String url = "../rent/rentList.jsp";
		listVO.setRentList(list);
		listVO.setPagingBean(pagingBean);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "rent-list");
		return TEMPLATE_PATH + "home.jsp";
	}
}