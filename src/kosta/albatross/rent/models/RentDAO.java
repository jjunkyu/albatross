package kosta.albatross.rent.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.book.models.BookVO;
import kosta.albatross.common.models.DataSourceManager;


public class RentDAO {
	private static RentDAO instance = new RentDAO();
	private Connection con;
	private String sql;
	private ResultSet rs;
	private DataSource ds;
	private PreparedStatement pstmt;
	
	private RentDAO() {
		ds = DataSourceManager.getInstance().getDataSource();
	}
	
	public static RentDAO getInstance() {
		return instance;
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
	
	public ArrayList<RentVO> rentList(String id) throws SQLException {
		ArrayList<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;
		try {
			con = ds.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT b.bNo,b.title,b.author,b.publisher,b.isRented, ");
			sql.append(" to_char(rentDate,'YYYY/MM/DD HH') , to_char(returnDate,'YYYY/MM/DD HH') ");
			sql.append(" FROM semi_book b, semi_rent_book rb ");
			sql.append(" WHERE rb.id = ? AND b.bNo = rb.bNo ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int isRented = 0;
				rentVO = new RentVO();
				rentVO.setBookVO(new BookVO());
				rentVO.getBookVO().setbNo(rs.getInt(1));
				rentVO.getBookVO().setTitle(rs.getString(2));
				rentVO.getBookVO().setAuthor(rs.getString(3));
				rentVO.getBookVO().setPublisher(rs.getString(4));
				isRented = rs.getInt(5);
				rentVO.setRentDate(rs.getString(6));
				rentVO.setReturnDate(rs.getString(7));
				if (isRented == 0) {
					rentVO.getBookVO().setRented(false);
				}else { 
					 rentVO.getBookVO().setRented(true);
				}
				list.add(rentVO);
			}
			return list;

		} finally {
			closeAll();
		}
	}
	
	public void addRentBook(String id,int bNo) throws SQLException {
		try {
			con = ds.getConnection();
			sql = "insert into SEMI_RENT_BOOK(id,bNo,rentdate) values(?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2, bNo);
			pstmt.executeUpdate();
		} finally {
			closeAll();
		}
	}

	public void returnRentBook(String id,int bNo) throws SQLException {
		try {
			con = ds.getConnection();
			sql = " UPDATE semi_rent_book SET returnDate = sysdate WHERE id = ? AND bNo = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, bNo);
			pstmt.executeUpdate();
		} finally {
			closeAll();
		}
	}
	
	public boolean isRentLate(int bNo) throws SQLException {
		int date = 0;
		try {
			con = (Connection) DataSourceManager.getInstance().getDataSource();
			sql = "select sysdate-rentdate from SEMI_RENT_BOOK where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if(rs.next())
				date = rs.getInt(1);
			if(date<7)
				return false;
			else
				return true;
		} finally {
			closeAll();
		}
	}
}
