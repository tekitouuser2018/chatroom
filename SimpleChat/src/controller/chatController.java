package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import database.dbManager;
import database.inputModel;

/**
 * Servlet implementation class chatController
 */
@WebServlet("/chat")
public class chatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	dbManager manager = new dbManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		//String name = (String)session.getAttribute("name");
		//request.setAttribute("name", name);

		ArrayList<inputModel	> allChat = new ArrayList<inputModel>();

		if(session== null)
		{
			response.sendRedirect("./login");
		}else{
			try {
				allChat = manager.getAllText();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			request.setAttribute("allChat", allChat);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/chatRoom.jsp");
			dispatcher.forward(request, response);

		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		String flag = request.getParameter("Flag");
		//System.out.println(flag);
		if(flag.equals("mine"))
		{
			ArrayList<inputModel	> allChat = new ArrayList<inputModel>();
			Integer id = -1;
			try {
				id = manager.getUserId((String)session.getAttribute("mail"),
						(String)session.getAttribute("pass"));
			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			//System.out.println("my id is :"+id);
			try {
				allChat = manager.getMineText(id);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			//System.out.println(allChat.size());
			//request.setAttribute("allChat", allChat);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/chatRoom.jsp");
			//dispatcher.forward(request, response);

			try{

				ArrayList<String> list = new ArrayList<>();


				for(int i =0; i<allChat.size(); i++){
				HashMap<String, String> jsonMap = new HashMap<String,String>();

				String name = allChat.get(i).getName();
				String text = allChat.get(i).getText();
				String date = allChat.get(i).getDate();
				jsonMap.put("name", name);
				jsonMap.put("text", text);
				jsonMap.put("time", date);


				//マッパ(JSON <-> Map, List)
				ObjectMapper mapper = new ObjectMapper();
				//json文字列
				String json = mapper.writeValueAsString(jsonMap);
				list.add(json);
				}
				//String responseJson = "{\"responseMessage\" : \"サーブレットからの返信です\"}";
				//ヘッダ設定
				response.setContentType("application/json;charset=UTF-8");
				//pwオブジェクト
				PrintWriter out = response.getWriter();
				 //出力
				out.print(list);
				//out.println(" { \"hoge\" : \"fuga\" } ");
				out.close();


				}catch (Exception e) {
					e.printStackTrace();
				}

		}else{

		try{
		//userInput input = new userInput();
		//input.setName(request.getParameter("Name"));
		//input.setText(request.getParameter("Text"));
		//input.setTime(request.getParameter("Time"));

		HashMap<String, String> jsonMap = new HashMap<String,String>();
		jsonMap.put("name", request.getParameter("Name"));
		jsonMap.put("text", request.getParameter("Text"));
		jsonMap.put("time", request.getParameter("Time"));

		//System.out.println(jsonMap.get("name"));
		//System.out.println(jsonMap.get("text"));

		Integer id = manager.getUserId((String)session.getAttribute("mail"),(String)session.getAttribute("pass") );

		manager.insertText(id, request.getParameter("Text"), request.getParameter("Time"));

		//マッパ(JSON <-> Map, List)
		ObjectMapper mapper = new ObjectMapper();
		//json文字列
		String json = mapper.writeValueAsString(jsonMap);

		//String responseJson = "{\"responseMessage\" : \"サーブレットからの返信です\"}";
		//ヘッダ設定
		response.setContentType("application/json;charset=UTF-8");
		//pwオブジェクト
		PrintWriter out = response.getWriter();
		 //出力
		out.print(json);
		//out.println(" { \"hoge\" : \"fuga\" } ");
		out.close();

		//ServletOutputStream outputStream = response.getOutputStream();
		//outputStream.print(" { \"hoge\" : \"fuga\" } ");
		//outputStream.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
}
