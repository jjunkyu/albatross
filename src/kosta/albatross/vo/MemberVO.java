package kosta.albatross.vo;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String address;
	private String cId;
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO(String id, String password, String name, String address, String cId) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.cId = cId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
}