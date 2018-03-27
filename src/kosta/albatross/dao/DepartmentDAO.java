package kosta.albatross.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.vo.DepartmentVO;

public class DepartmentDAO {
	
	private static DepartmentDAO instance = new DepartmentDAO();
	private Connection con;
	private String sql;
	private ResultSet rs;
	private DataSource ds;
	private PreparedStatement pstmt;
	
	private DepartmentDAO() {
		ds = DataSourceManager.getInstance().getDataSource();
	}
	
	public static DepartmentDAO getInstance() {
		return instance;
	}
	
	public ArrayList<DepartmentVO> getDepartmentList() {
		ArrayList<DepartmentVO> list = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			sql = "SELECT deptno, dname FROM department";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(!rs.next()) {
				DepartmentVO vo = new DepartmentVO();
				vo.setDeptno(rs.getInt(1));
				vo.setDname(rs.getString(2));
				list.add(vo);
			}
		} catch(SQLException e) {
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
	
	public void registerDepartment(String dno, String dname, String loc, String tel) {
		try {
			con = ds.getConnection();
			sql = "INSERT INTO "
					+ "department (deptno, dname, loc, tel) "
					+ "VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  dno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			pstmt.setString(4, tel);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}
}	
