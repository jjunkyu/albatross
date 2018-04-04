package kosta.albatross.common.models;

import java.util.ArrayList;

import kosta.albatross.book.models.BookVO;
import kosta.albatross.post.models.PostVO;
import kosta.albatross.rent.models.RentVO;

public class ListVO {
	private ArrayList<PostVO> postList = new ArrayList<PostVO>();
	private ArrayList<BookVO> bookList = new ArrayList<BookVO>();
	private ArrayList<RentVO> rentList = new ArrayList<RentVO>();

	PagingBean pagingBean = new PagingBean();

	public ListVO() {
		super();
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
	
	public ArrayList<RentVO> getRentList() {
		return rentList;
	}

	public void setRentList(ArrayList<RentVO> rentList) {
		this.rentList = rentList;
	}
}
