package day0905;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1.��������
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ����
		String url="jdbc:mysql://127.0.0.1/c83-s2-pljj-damai";
		String user="root";
		String password="a";
		Connection conn=DriverManager.getConnection(url, user, password);
		//3.�������
		Statement stat=conn.createStatement();
		String sql="delete from dm_category where id=82";
		int rows=stat.executeUpdate(sql);
		conn.close();
	}
}
