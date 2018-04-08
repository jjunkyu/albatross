package com.kosta.albatross.post.models;

import com.kosta.albatross.member.models.MemberVO;

public class PostVO {
	private int pNo;
	private String title;
	private String content;
	private int hits;
	private String timePosted;
	private MemberVO memberVO;
	
	public PostVO() {
		super();
	}

	public PostVO(int pNo, String title, String content, int hits, String timePosted, MemberVO memberVO) {
		super();
		this.pNo = pNo;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.timePosted = timePosted;
		this.memberVO = memberVO;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
}
