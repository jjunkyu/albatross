package kosta.albatross.member.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import kosta.albatross.common.models.DataSourceManager;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private Connection con;
	private String sql;
	private ResultSet rs;
	private DataSource dataSource;
	private PreparedStatement pstmt;
	
	private MemberDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static MemberDAO getInstance() {
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
	
	/**
	 * 로그인 메소드
	 * @param id
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public MemberVO login(String id, String password) throws SQLException {
		MemberVO vo = null;
		try {
			con=dataSource.getConnection();
			sql="select * from semi_member where id=? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo= new MemberVO();
						vo.setId(rs.getString(1));
						vo.setPassword(rs.getString(2));
						vo.setAddress(rs.getString(3));
						vo.setName(rs.getString(4));
						vo.setEmail(rs.getString(5));
						vo.setAnswer(rs.getString(6));
						vo.setcId(rs.getString(7));
						vo.setqId(rs.getString(8));
					} 
		}finally {
			closeAll();
		}
		return vo;
	}
	/**
	 * 회원가입 메서드
	 * @param memberVO
	 * @throws SQLException
	 */
	public void register(MemberVO memberVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			String sql="insert into semi_member(id,password,name,address) values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getAddress());
			pstmt.executeUpdate();
		}finally{
			closeAll();
		}
	}
	public boolean idCheck(String id) throws SQLException{
		boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=dataSource.getConnection();
			String sql="select count(*) from semi_member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()&&(rs.getInt(1)>0))
			flag=true;			
		}finally{
			closeAll();
		}
		return flag;
	}

	public ArrayList<String> getMemberFindView() throws SQLException {
		ArrayList<String>list = new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select query from question";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} finally {
			closeAll();
		}
		
		return list;
	}

	public String getMemberFindQid(String email) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qid=null;
		try {
			con=dataSource.getConnection();
			String sql="select qid from semi_member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				qid=rs.getString(1);
			}
		} finally {
			closeAll();
		}
		
		return qid;
	}

	public MemberVO getMemberFind(String email, String answer, String qid) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO mvo = null;
		try {
			con=dataSource.getConnection();
			String sql="select id , password from semi_member where email=? and answer=? and qid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, answer);
			pstmt.setString(3, qid);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				mvo=new MemberVO(rs.getString(1),rs.getString(2),null,null,null);
			}
		} finally {
			closeAll();
		}
		
		return mvo;
	}
	
}
