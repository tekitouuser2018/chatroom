package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class dbManager {

	public Connection getConnection()throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String dbURL ="jdbc:sqlite:‪SimpleChat.info";
		Connection connection = DriverManager.getConnection(dbURL);

		System.out.println("Connected to Database...");
		return connection;
	}

	public  void closeConnection(Connection connection) {
		if(connection != null){
			try{
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Connection was Closed.");
		}else{
			System.out.println("Connection is NUll!!");
		}
	}

	public boolean login(String mail, String password)throws ClassNotFoundException, SQLException{

		Connection connection = getConnection();
		boolean ok = false;

		try{
			String pwd = passwordUtil.getSafetyPassword(password, mail);
			System.out.println(mail+" : "+pwd);
			String sqlString =
					"select * from user where mail=? and password=?";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.setString(1, mail);
			statement.setString(2, pwd);
			ResultSet rs	= statement.executeQuery();

			if(!rs.next()){
				System.out.println("login error");
				throw new SecurityException("mail or password incorrect");
			}else {
				String getid = rs.getString(1);
				String getname	 = rs.getString(4);
				System.out.println(getid+" : "+getname);
				ok = true;
			}
		}finally {
			closeConnection(connection);
			return ok;
		}
	}

	public void selectAllFromUser()throws ClassNotFoundException, SQLException{

		Connection connection = getConnection();
		try{
			String sqlString =
					"select * from user";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			ResultSet rs	= statement.executeQuery();


			while(rs.next()){
				System.out.println("ID: "+rs.getString(1)+", Mail : "+rs.getString(2)
				+", Pass: "+rs.getString(3)+", Name: "+rs.getString(4)
				+", Date: "+rs.getString(5));
			}

		}finally {
			closeConnection(connection);
		}
	}

	public void selectAllFromInput()throws ClassNotFoundException, SQLException{

		Connection connection = getConnection();
		try{
			String sqlString =
					"select * from inputData";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			ResultSet rs	= statement.executeQuery();


			while(rs.next()){
				System.out.println("ID: "+rs.getString(1)+", Text : "+rs.getString(2)
				+", Date: "+rs.getString(3));
			}

		}finally {
			closeConnection(connection);
		}
	}


	public ArrayList<inputModel> getAllText()throws ClassNotFoundException, SQLException{

		ArrayList<inputModel> list = new ArrayList<inputModel>();
		Connection connection = getConnection();
		try{
			String sqlString =
					"select * from inputData";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			ResultSet rs	= statement.executeQuery();


			while(rs.next()){
				Integer id = rs.getInt(1);
				String name = getUserName(id);
				String text = rs.getString(2);
				String date = rs.getString(3);

				inputModel textData = new inputModel();
				textData.setName(name);
				textData.setText(text);
				textData.setDate(date);
				list.add(textData);
			}

		}finally {
			closeConnection(connection);
			return list;
		}
	}

	public ArrayList<inputModel> getMineText(Integer id)throws ClassNotFoundException, SQLException{

		ArrayList<inputModel> list = new ArrayList<inputModel>();
		Connection connection = getConnection();
		try{
			String sqlString =
					"select * from inputData where id=?";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.setString(1,id.toString());
			ResultSet rs	= statement.executeQuery();


			while(rs.next()){

				String name = getUserName(id);
				String text = rs.getString(2);
				String date = rs.getString(3);
				//System.out.println(name +" : "+text+" : "+date);

				inputModel textData = new inputModel();
				textData.setName(name);
				textData.setText(text);
				textData.setDate(date);
				list.add(textData);
			}

		}finally {
			closeConnection(connection);
			return list;
		}
	}



	public String getUserName(Integer id)throws ClassNotFoundException, SQLException{

		String name = "名無し";
		Connection connection = getConnection();
		try{
			//String pwd = passwordUtil.getSafetyPassword(password, mail);

			String sqlString =
					"select name from user where id=? ";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.setString(1, id.toString());

			ResultSet rs	= statement.executeQuery();


			while(rs.next()){
				name = rs.getString(1);

			}

		}finally {
			closeConnection(connection);
			return name;
		}
	}

	public String getUserName(String mail,String password)throws ClassNotFoundException, SQLException{

		String name = "名無し";
		Connection connection = getConnection();
		try{
			String pwd = passwordUtil.getSafetyPassword(password, mail);

			String sqlString =
					"select name from user where mail=? and password=?";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.setString(1, mail);
			statement.setString(2, pwd);
			ResultSet rs	= statement.executeQuery();


			while(rs.next()){
				name = rs.getString(1);
			}

		}finally {
			closeConnection(connection);
			return name;
		}
	}

	public Integer getUserId(String mail, String password)throws ClassNotFoundException, SQLException{

		Integer id = -1;
		Connection connection = getConnection();
		try{
			String pwd = passwordUtil.getSafetyPassword(password, mail);

			String sqlString =
					"select id from user where mail=? and password=?";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.setString(1, mail);
			statement.setString(2, pwd);
			ResultSet rs	= statement.executeQuery();


			while(rs.next()){
				id = rs.getInt(1);
			}

		}finally {
			closeConnection(connection);
			return id;
		}
	}


	public void createTable()throws ClassNotFoundException, SQLException{

		Connection connection = getConnection();
		try{

			String sqlString =
					"create table inputData("
					+"id integer not null,"
					+ "input text ,"
					+ "date text not null"
					+ ")";
			PreparedStatement statement = connection.prepareStatement(sqlString);
			statement.executeUpdate();

			System.out.println("inputData table is created");


			}catch (Exception e) {
				System.out.println("create table error");
				e.printStackTrace();
			}finally{
				closeConnection(connection);
			}

	}

	public void updatePass(String password, String mail)throws ClassNotFoundException, SQLException{

		Connection connection = getConnection();
		try{

			String pwd = passwordUtil.getSafetyPassword(password, mail);
			String  sqlString= "update user set password = ? where mail = ?";

			PreparedStatement  statement = connection.prepareStatement(sqlString);

			statement.setString(1, pwd);
			statement.setString(2, mail);


			statement.executeUpdate();

			System.out.println("password was updated");

			}catch (Exception e) {
				System.out.println("password update error");
				e.printStackTrace();
			}finally{
				closeConnection(connection);
			}

	}

	public boolean insertUser(String password, String mail,String name,String date)throws ClassNotFoundException, SQLException{
		boolean ok = false;

		Connection connection = getConnection();
		try{

			String pwd = passwordUtil.getSafetyPassword(password, mail);
			String  sqlString= "insert into user (mail,password,name ,date)"
					+ "values(?,?,?,?)";

			PreparedStatement  statement = connection.prepareStatement(sqlString);

			statement.setString(1, mail);
			statement.setString(2, pwd);
			statement.setString(3, name);
			statement.setString(4, date);


			statement.executeUpdate();

			System.out.println("new user was inserted");
			ok = true;

			}catch (Exception e) {
				System.out.println("insert user error");
				e.printStackTrace();

			}finally{
				closeConnection(connection);
				return ok;
			}

	}


	public boolean insertText(Integer id, String text,String date)throws ClassNotFoundException, SQLException{
		boolean ok = false;

		Connection connection = getConnection();
		try{

			//String pwd = passwordUtil.getSafetyPassword(password, mail);
			String  sqlString= "insert into inputData "
					+ "values(?,?,?)";

			PreparedStatement  statement = connection.prepareStatement(sqlString);

			statement.setInt(1, id);
			statement.setString(2, text);
			statement.setString(3, date);


			statement.executeUpdate();

			System.out.println("new text was inserted");
			ok = true;

			}catch (Exception e) {
				System.out.println("insert inputData error");
				e.printStackTrace();

			}finally{
				closeConnection(connection);
				return ok;
			}

	}
}
