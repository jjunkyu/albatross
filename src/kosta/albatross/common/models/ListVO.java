package kosta.albatross.common.models;

import java.util.ArrayList;

import kosta.albatross.book.models.BookVO;
import kosta.albatross.post.models.PostVO;

public class ListVO {
	ArrayList<PostVO> postList = new ArrayList<PostVO>();
	ArrayList<BookVO> bookList = new ArrayList<BookVO>();
	PagingBean pagingBean = new PagingBean();

	public ListVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ListVO(ArrayList<PostVO> postList, PagingBean pagingBean) {
		super();
		this.postList = postList;
		this.pagingBean = pagingBean;
	}


	public ArrayList<PostVO> getPostList() {
		return postList;
	}

	public void setPostList(ArrayList<PostVO> postList) {
		this.postList = postList;
	}

	public ArrayList<BookVO> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<BookVO> bookList) {
		this.bookList = bookList;
	}

	public PagingBean getPagingBean() {
		return pagingBean;
	}

	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}

}
