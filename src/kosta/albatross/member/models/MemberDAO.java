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
	private DataSource dataSource;

	private MemberDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	private void closeAll(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeAll(Connection con, PreparedStatement pstmt) {
		try {
			if (con != null)
				con.close();
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 로그인 메소드
	 * 
	 * @param id
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public MemberVO login(String id, String password) throws SQLException {
		MemberVO vo = null;
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM SEMI_MEMBER WHERE id=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setPassword(rs.getString(2));
				vo.setAddress(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setAnswer(rs.getString(6));
				vo.setcId(rs.getString(7));
				vo.setqId(rs.getString(8));
			}
		} finally {
			closeAll(con, pstmt, rs);
		}
		return vo;
	}

	/**
	 * 회원가입 메서드
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void register(MemberVO memberVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();

			String sql = "INSERT INTO SEMI_MEMBER(id,password,address,name,eMail,answer,qId) VALUES(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getAddress());
			pstmt.setString(4, memberVO.getName());
			pstmt.setString(5, memberVO.getEmail());
			pstmt.setString(6, memberVO.getAnswer());
			pstmt.setString(7, memberVO.getqId());
			pstmt.executeUpdate();
		} finally {
			closeAll(con, pstmt);
		}
	}

	/**
	 * 회원가입 시 질문지 목록 받아오는 메서드
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> questionList() throws SQLException {
		ArrayList<String> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT query FROM QUESTION";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} finally {
			closeAll(con, pstmt, rs);
		}
		return list;
	}

	/**
	 * 회원가입 시 아이디 중복확인하는 메소드
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean idCheck(String id) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM SEMI_MEMBER WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next() && (rs.getInt(1) > 0))
				flag = true;
		} finally {
			closeAll(con, pstmt, rs);
		}
		return flag;
	}

	/**
	 * 아이디/패스워드 찾기위해서 해당메일 정보에 있는 qid값 호출메소드.
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public String getMemberFindQid(String email) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String qid = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT qid FROM SEMI_MEMBER WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				qid = rs.getString(1);
			}
		} finally {
			closeAll(con, pstmt, rs);
		}

		return qid;
	}

	/**
	 * 아이디/패스워드 찾기 메소드
	 * 
	 * @param email
	 * @param answer
	 * @param qid
	 * @return
	 * @throws SQLException
	 */
	public MemberVO getMemberFind(String email, String answer, String qid) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO mvo = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT id , password FROM semi_member WHERE email=? AND answer=? AND qid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, answer);
			pstmt.setString(3, qid);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				mvo = new MemberVO(rs.getString(1), rs.getString(2), null, null, null);
			}
		} finally {
			closeAll(con, pstmt, rs);
		}

		return mvo;
	}

	/**
	 * 회원가입시 이메일 중복검사 메서드
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public boolean emailCheck(String email) throws SQLException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM SEMI_MEMBER WHERE eMail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next() && (rs.getInt(1) > 0))
				flag = true;
		} finally {
			closeAll(con, pstmt, rs);
		}
		return flag;
	}

	/**
	 * 회원정보 수정시 질문지 받아오기 메서드
	 * @param qid
	 * @return
	 * @throws SQLException
	 */
	public String questionQuery(String qid) throws SQLException {
		String query=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="SELECT query FROM QUESTION WHERE qid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, qid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				query = rs.getString(1);
			}
		}finally {
			closeAll(con, pstmt, rs);
		}
		return query;
	}
	/**
	 * 회원정보 수정하여 회원정보 수정한 것 다시 보여주는 메서드
	 * @param password
	 * @param name
	 * @param address
	 * @param answer
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public MemberVO memberUpdate(String password, String name,String address, String answer,String id) throws SQLException {
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="UPDATE SEMI_MEMBER SET password=?,name=?,address=?,answer=? WHERE id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, answer);
			pstmt.setString(5, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			String sql2="SELECT * FROM SEMI_MEMBER WHERE id=?";
			pstmt=con.prepareStatement(sql2);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
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
			closeAll(con, pstmt, rs);
		}
		return vo;
	}
}
