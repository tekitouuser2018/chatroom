package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dbManager;

/**
 * Servlet implementation class registController
 */
@WebServlet("/register")
public class registController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	dbManager manager = new dbManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String password = request.getParameter("Password");
		String mail = request.getParameter("Mail");
		String name = request.getParameter("Name");
		String date = request.getParameter("date");
		name = name.equals("")? "名無し":name;

		try {
				manager.insertUser(password, mail, name, date);
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				//dispatcher.forward(request, response);
				response.sendRedirect("./login");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.out.println("ユーザー名かパスワードを変更してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("ユーザー名かパスワードを変更してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register.jsp");
			dispatcher.forward(request, response);
		}


	}

}
