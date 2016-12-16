package project.tarena.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���Ӳ������ݿ�Ĺ�����
 * @author Administrator
 *
 */
public class JdbcUtil {
	/**
	 * ��������
	 */
	static{
		try {
			Class.forName(PropertiesUtil.getValues("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��������Connection
	 * @return
	 */
	public static Connection getConnection(){
		Connection connection=null;
		String url=PropertiesUtil.getValues("url");
		String user=PropertiesUtil.getValues("user");
		String password=PropertiesUtil.getValues("password");
		try {
			connection=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * �õ�PreparedStatement����
	 * @param connection	connection����
	 * @param sql		sql���
	 * @param objects	��̬����
	 * @return	PreparedStatement����
	 */
	public static PreparedStatement getPs(Connection connection,String sql, Object... objects){
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	/**
	 * �����Ĺر���
	 * @param rs	ResultSet ����
	 * @param ps	PreparedStatement����
	 * @param connection	Connection ����
	 */
	public static void Close(ResultSet rs,PreparedStatement ps,Connection connection){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
