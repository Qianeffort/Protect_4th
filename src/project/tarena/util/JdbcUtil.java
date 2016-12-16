package project.tarena.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 连接操作数据库的工具类
 * @author Administrator
 *
 */
public class JdbcUtil {
	/**
	 * 加载驱动
	 */
	static{
		try {
			Class.forName(PropertiesUtil.getValues("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建连接Connection
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
	 * 得到PreparedStatement对象
	 * @param connection	connection对象
	 * @param sql		sql语句
	 * @param objects	动态参数
	 * @return	PreparedStatement对象
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
	 * 公共的关闭类
	 * @param rs	ResultSet 对象
	 * @param ps	PreparedStatement对象
	 * @param connection	Connection 对象
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
