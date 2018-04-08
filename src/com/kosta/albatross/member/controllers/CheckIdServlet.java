package com.kosta.albatross.member.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.albatross.member.models.MemberDAO;

/**
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/CheckIdServlet")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String command=request.getParameter("checkAjax");
		if(command.equals("idcheck")){
			String id=request.getParameter("id");
			try {
				boolean flag= MemberDAO.getInstance().idCheck(id);
				if(flag){
					out.print("fail"); //사용가능
				}else{
					out.print("ok");// 사용불가
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		out.close();
	}

}
