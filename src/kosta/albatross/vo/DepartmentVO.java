package kosta.albatross.vo;

public class DepartmentVO {
	private int dno;
	private String dname;
	private String loc;
	private String tel;
	
	public DepartmentVO() {}
	public int getDeptno() {
		return dno;
	}
	public void setDeptno(int dno) {
		this.dno = dno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
