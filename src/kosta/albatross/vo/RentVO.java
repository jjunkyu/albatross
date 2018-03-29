package kosta.albatross.vo;

public class RentVO {
	private MemberVO memberVO;
	private BookVO bookVO;
	private String rentDate;
	private String returnDate;
	
	public RentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RentVO(MemberVO memberVO, BookVO bookVO, String rentDate, String returnDate) {
		super();
		this.memberVO = memberVO;
		this.bookVO = bookVO;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
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
}
