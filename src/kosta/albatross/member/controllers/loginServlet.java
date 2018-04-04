package kosta.albatross.member.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kosta.albatross.member.models.MemberDAO;
import kosta.albatross.member.models.MemberVO;

/**
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/LoginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String command = request.getParameter("checkAjaxlogin");
		if(command.equals("logincheck")){
			String id = request.getParameter("userID");
			String password = request.getParameter("userPassword");
			HttpSession session = request.getSession();
			try {
				MemberVO loginVO = MemberDAO.getInstance().login(id, password);
				if(loginVO != null){
					out.print("ok"); //로그인성공
					session.setAttribute("loginVO", loginVO);
					session.setAttribute("pNoList", new ArrayList<Integer>());
				}else{
					out.print("fail");
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
			
		}	
		out.close();
	}

}
