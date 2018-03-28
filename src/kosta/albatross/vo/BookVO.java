package kosta.albatross.vo;

public class BookVO {
	private int bNo;
	private String title;
	private String content;
	private String author;
	private String publisher;
	private boolean isRented;
	
	public BookVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookVO(int bNo, String title, String content, String author, String publisher) {
		super();
		this.bNo = bNo;
		this.title = title;
		this.content = content;
		this.author = author;
		this.publisher = publisher;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public boolean isRented() {
		return isRented;
	}
	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}
	
}
