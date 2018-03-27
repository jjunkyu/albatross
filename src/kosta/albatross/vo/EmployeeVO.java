package kosta.albatross.vo;

public class EmployeeVO {
	private int eno;
	private String ename;
	private int salary;
	private int deptno;
	private DepartmentVO dvo;
	
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public DepartmentVO getDvo() {
		return dvo;
	}
	public void setDvo(DepartmentVO dvo) {
		this.dvo = dvo;
	}
	
}
