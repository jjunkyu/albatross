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
	private DataSource dataSource;
	private static RentDAO instance = new RentDAO();
	
	private RentDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static RentDAO getInstance() {
		return instance;
	}
	
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void closeAll(PreparedStatement pstmt, Connection con) {

		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	/**
	 * 책을 빌린 목록을 보여주는 메서드
	 * 
	 * @param id
	 * @return list
	 * @throws SQLException
	 */
	public ArrayList<RentVO> rentList(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RentVO> list = new ArrayList<RentVO>();
		RentVO rentVO = null;
		try {
			con = dataSource.getConnection();
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
			closeAll(rs, pstmt, con);
		}
	}

	/**
	 * 책을 빌릴때 목록에 추가하는 메서드
	 * 
	 * @param id
	 * @param bNo
	 * @throws SQLException
	 */
	public void addRentBook(String id,int bNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO semi_rent_book(rId,id,bNo,rentdate) VALUES(semi_rent_book_seq.nextval,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2, bNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(rs, pstmt, con);
		}
	}

	/**
	 * 빌린 책을 반납할 때 쓰는 메서드
	 * 
	 * @param id
	 * @param bNo
	 * @throws SQLException
	 */
	public void returnRentBook(String id,int bNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = " UPDATE semi_rent_book SET returnDate = sysdate WHERE id = ? AND bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, bNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(rs, pstmt, con);
		}
	}
	/**
	 * 책을 빌렸는지 상태를 확인하는 메서드
	 * true 일 경우 책을 빌린 상태
	 * false 일 경우 책을 빌리지 않은 상태
	 * @param bNo
	 * @return true or false
	 * @throws SQLException
	 */
	public boolean isRentLate(int bNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int date = 0;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT sysdate-rentdate FROM SEMI_RENT_BOOK WHERE bNo = ?";
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
			closeAll(rs, pstmt, con);
		}
	}
	
	/**
	 * bNo에 해당하는 책이 빌려지고 있는지 확인하는 메서드
	 * @param bNo
	 * @return
	 * @throws SQLException
	 */
	public boolean isRentingBook(int bNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM semi_rent_book WHERE bNo=? and returnDate IS NULL";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)!=0)
					flag = true;
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return flag;
	}
}
