package com.kosta.albatross.post.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.albatross.Controller;
import com.kosta.albatross.member.models.MemberVO;
import com.kosta.albatross.post.models.PostDAO;
import com.kosta.albatross.post.models.PostVO;

public class PostWriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("loginVO") == null) {
			return "index.jsp";
		}else {
			MemberVO memberVO = (MemberVO)session.getAttribute("loginVO");
			PostVO pVO = new PostVO();
			MemberVO mVO = new MemberVO();
			mVO.setId(memberVO.getId());
			pVO.setTitle(request.getParameter("title"));
			pVO.setContent(request.getParameter("content"));
			pVO.setMemberVO(mVO);
			PostVO postVO = PostDAO.getInstance().posting(pVO);
			return REDIRECT_PREFIX + "dispatcher?command=postDetail&pNo="+postVO.getpNo();
		}
	}

}
