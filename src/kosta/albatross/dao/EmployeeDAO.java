package kosta.albatross.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.vo.EmployeeVO;

public class EmployeeDAO {
	private static EmployeeDAO instance = new EmployeeDAO();
	Connection con;
	String sql;
	ResultSet rs;
	DataSource ds;
	PreparedStatement pstmt;
	
	private EmployeeDAO() {
		ds = DataSourceManager.getInstance().getDataSource();
	}
	
	public static EmployeeDAO getInstance() {
		return instance;
	}
	
	public ArrayList<EmployeeVO> getEmployeeList(){
		ArrayList<EmployeeVO> list = new ArrayList<>();
		
		
		try {
			con = ds.getConnection();
			String sql = 
					"SELECT e.empno, e.name, e.sal, d.deptno, d.dname, d.loc, d.tel "
					+ "FROM employee e, department d "
					+ "WHERE e.deptno == d.deptno";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(!rs.next()) {
				list.add(new EmployeeVO());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}		
		return list;
	}
	private void closeAll() {
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void registerEmployee(String dname, String loc, String tel) {
		try {
			con = ds.getConnection();
			sql = "INSERT INTO department VALUES (?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dname);
			pstmt.setString(2,  loc);
			pstmt.setString(3, tel);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}
}
