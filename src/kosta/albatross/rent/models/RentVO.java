package kosta.albatross.rent.models;

import kosta.albatross.book.models.BookVO;

public class RentVO {
	private int rId;
	private BookVO bookVO;
	private String rentDate;
	private String returnDate;
	
	public RentVO() {
		super();
	}

	public RentVO(BookVO bookVO, String rentDate, String returnDate) {
		super();
		this.bookVO = bookVO;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
	}
	
	public BookVO getBookVO() {
		return bookVO;
	}
	
	public void setBookVO(BookVO bookVO) {
		this.bookVO = bookVO;
	}
	
	public String getRentDate() {
		return rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}
	
}
