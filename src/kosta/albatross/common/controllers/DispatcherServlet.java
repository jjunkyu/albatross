package kosta.albatross.common.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			handleRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			handleRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = HandlerMapping.getInstance().create(request.getParameter("command")).execute(request, response);
		if(url.startsWith(Controller.REDIRECT_PREFIX)) {
			response.sendRedirect(url.substring(Controller.REDIRECT_PREFIX.length()));
		} else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}