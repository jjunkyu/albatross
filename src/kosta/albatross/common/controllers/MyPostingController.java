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
		String pno = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		int totalCount = PostDAO.getInstance().getTotalPostCountbyId(memberVO.getId());
		if (pno == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pno));
		}
		//paging
		ArrayList<PostVO> plist = PostDAO.getInstance().getPostList(pagingBean);
		ListVO listVO = new ListVO(plist, pagingBean);
		
		ArrayList<PostVO> list = PostDAO.getInstance().getMyPostin(memberVO.getId());
		String url = "/member/myPosting.jsp";
		request.setAttribute("list", list);
		request.setAttribute("plist", plist);
		request.setAttribute("listVO", listVO);
		request.setAttribute("url", url);
		request.setAttribute("page", "page-myAccount-myPosting");
		return TEMPLATE_PATH + "home.jsp";
		
	}

}
