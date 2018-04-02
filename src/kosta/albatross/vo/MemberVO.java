package kosta.albatross.vo;

import java.util.ArrayList;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String address;
	private String cId;
	private ArrayList<RentVO> rentList;
	private String email;
	private String answer;
	private String qId;
	public MemberVO() {
		super();
	}
	
	public MemberVO(String id, String password, String address, String name, String cId) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.cId = cId;
	}
	
	
	public String getqId() {
		return qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + ", cId="
				+ cId + "]";
	}
	public ArrayList<RentVO> getRentList() {
		return rentList;
	}
	
	public void setRentList(ArrayList<RentVO> rentList) {
		this.rentList = rentList;
	}
}
