package kosta.albatross.common.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.common.models.ListVO;
import kosta.albatross.common.models.PagingBean;
import kosta.albatross.member.models.MemberVO;
import kosta.albatross.post.models.PostDAO;
import kosta.albatross.post.models.PostVO;

public class MyPostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("loginVO");
		if (session == null || memberVO == null) {
			return REDIRECT_PREFIX + "index.jsp";
		}
		String pNo = request.getParameter("pNo");
		PagingBean pagingBean = null;
		int totalCount = PostDAO.getInstance().getTotalPostCountbyId(memberVO.getId());
		if (pNo == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pNo));
		}
		//paging
		ArrayList<PostVO> list = PostDAO.getInstance().getMyPosting(memberVO.getId(),pagingBean);
		ListVO listVO = new ListVO(list, pagingBean);
		String url = "/member/myPosting.jsp";
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "page-myAccount-myPosting");
		return TEMPLATE_PATH + "home.jsp";
	}
}
