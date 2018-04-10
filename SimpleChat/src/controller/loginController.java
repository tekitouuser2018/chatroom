package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dbManager;
/**
 * Servlet implementation class loginController
 */
@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	dbManager manager = new dbManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mail = request.getParameter("Mail");
		String pass = request.getParameter("Password");
		manager = new dbManager();

		HttpSession session = request.getSession(true);



		try {
			if(manager.login(mail	,pass)==true){
				System.out.println("login returned true");
				session.setAttribute("mail", mail);
				session.setAttribute("pass", pass);
				session.setAttribute("status", true);
				session.setMaxInactiveInterval(60*60*24*7);
				manager = new dbManager();
				String name = manager.getUserName(mail, pass);
				session.setAttribute("name", name);

				response.sendRedirect("./chat");

			}else{
				System.out.println("login returned false");
				doGet(request, response);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("login returned error");
			e.printStackTrace();
			doGet(request, response);
		} catch (SQLException e) {
			System.out.println("login returned error");
			e.printStackTrace();
			doGet(request, response);
		}
	}

}
