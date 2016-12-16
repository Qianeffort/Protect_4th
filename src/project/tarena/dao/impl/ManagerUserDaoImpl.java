package project.tarena.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import project.tarena.dao.ManagerUserDao;
import project.tarena.entity.ManagerUser;
import project.tarena.util.JdbcUtil;

public class ManagerUserDaoImpl implements ManagerUserDao {

	@Override
	public ManagerUser findByName(String name) {
		//创建sql语句
		String sql="SELECT * FROM manager_user WHERE managerName=?";
		//得到数据库连接
		Connection connection=JdbcUtil.getConnection();
		//得到PreparedStatement对象
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, name);
		ResultSet rs=null;
		ManagerUser manageruser=null;
		try {
			//执行语句
			rs=ps.executeQuery();
			//处理结果，返回结果集
			while(rs.next()){
				manageruser=new ManagerUser();
				manageruser.setId(rs.getInt("id"));
				manageruser.setRealName(rs.getString("realName"));
				manageruser.setManagerName(rs.getString("managerName"));
				manageruser.setPassWord(rs.getString("passWord"));
				manageruser.setPhoneNum(rs.getString("phoneNum"));
				manageruser.setEmail(rs.getString("email"));
				manageruser.setAuthorization_date(rs.getString("authorization_date"));
				manageruser.setLastlogintime(rs.getString("lastlogintime"));
				manageruser.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(rs, ps, connection);
		}
		return manageruser;
	}

	@Override
	public ManagerUser findById(int id) {
		//创建sql语句
				String sql="SELECT * FROM manager_user WHERE id=?";
				//得到数据库连接
				Connection connection=JdbcUtil.getConnection();
				//得到PreparedStatement对象
				PreparedStatement ps=JdbcUtil.getPs(connection, sql, id);
				ResultSet rs=null;
				ManagerUser manageruser=null;
				try {
					//执行语句
					rs=ps.executeQuery();
					//处理结果，返回结果集
					while(rs.next()){
						manageruser=new ManagerUser();
						manageruser.setId(rs.getInt("id"));
						manageruser.setRealName(rs.getString("realName"));
						manageruser.setManagerName(rs.getString("managerName"));
						manageruser.setPassWord(rs.getString("passWord"));
						manageruser.setPhoneNum(rs.getString("phoneNum"));
						manageruser.setEmail(rs.getString("email"));
						manageruser.setAuthorization_date(rs.getString("authorization_date"));
						manageruser.setLastlogintime("lastlogintime");
						manageruser.setRole(rs.getString("role"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					JdbcUtil.Close(rs, ps, connection);
				}
				return manageruser;
			}

	@Override
	public List<ManagerUser> findManaUserList(String manaName) {
		//创建sql语句
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT * FROM manager_user");
		//判断manaName有值时，进行sql语句拼接进行管理员帐号模糊查询
		//true表示需要传递管理员帐号模糊查询，false表示不需要
		boolean flag=false;
		if(manaName!=null&&manaName.trim().length()!=0){
			sql.append(" where managerName like ?");
			flag=true;
		}
		//创建connection连接
		Connection connection=JdbcUtil.getConnection();
		//得到PreparedStatement对象
		PreparedStatement ps=null;
		if(flag){
			ps=JdbcUtil.getPs(connection, sql.toString(), "%"+manaName+"%");
		}else{
			ps=JdbcUtil.getPs(connection, sql.toString());
		}
		ResultSet rs=null;
		List<ManagerUser> list=Collections.EMPTY_LIST;
		ManagerUser managerUser=null;
		try {
			//执行语句
			rs=ps.executeQuery();
			//处理结果
			while(rs.next()){
				if(list.isEmpty()){
					list=new ArrayList<ManagerUser>();
				}
				managerUser=new ManagerUser();
				managerUser.setId(rs.getInt("id"));
				managerUser.setRealName(rs.getString("realName"));
				managerUser.setManagerName(rs.getString("managerName"));
				managerUser.setPassWord(rs.getString("passWord"));
				managerUser.setPhoneNum(rs.getString("phoneNum"));
				managerUser.setEmail(rs.getString("email"));
				managerUser.setAuthorization_date(rs.getString("authorization_date"));
				managerUser.setRole(rs.getString("role"));
				list.add(managerUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(rs, ps, connection);
		}
		
		return list;
	}

	@Override
	public void addManaUser(ManagerUser manaUser) {
		//创建sql语句
		String sql="insert into manager_user(realName,managerName,role,authorization_date,password,phoneNum,email) values(?,?,?,?,?,?,?)";
		//得到连接
		Connection connection=JdbcUtil.getConnection();
		//得到preparedstatement对象
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, manaUser.getRealName(),manaUser.getManagerName(),manaUser.getRole(),
				manaUser.getAuthorization_date(),manaUser.getPassWord(),manaUser.getPhoneNum(),manaUser.getEmail());
		try {
			//执行语句
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(null, ps, connection);
		}
		
	}

	@Override
	public void delManaUser(int id) {
		//创建sql语句
		String sql="delete from manager_user where id=?";
		//得到连接
		Connection connection=JdbcUtil.getConnection();
		//得到PreparedStatement对象
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, id);
		try {
			//执行语句
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(null, ps, connection);
		}
		
	}

	@Override
	public void resetManaUserpwd(int id) {
		//创建sql语句
		String sql="update manager_user set passWord=123456 where id=?";
		//获得Connection对象
		Connection connection=JdbcUtil.getConnection();
		//获得PreparedStatement对象
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, id);
		try {
			//执行语句
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(null, ps, connection);
		}
		
	}

	@Override
	public void modiManaUser(ManagerUser managerUser) {
		//创建Sql语句
		String sql="update manager_user set realName=?,phoneNum=?,email=? where id=?";
		//获取Connection对象
		Connection connection=JdbcUtil.getConnection();
		//获取PreparedStatement对象
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, managerUser.getRealName(),managerUser.getPhoneNum(),managerUser.getEmail(),managerUser.getId());
		try {
			//执行语句
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(null, ps, connection);
		}
	}

	@Override
	public void updateTime(ManagerUser managerUser) {
		String sql = "update manager_user set lastlogintime=? where id=?";
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement ps = JdbcUtil.getPs(connection, sql, managerUser.getLastlogintime(), managerUser.getId());
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.Close(null, ps, connection);
		}
		
	}

	@Override
	public void modiPassWord(ManagerUser managerUser) {
		String sql = "update manager_user set passWord=? where id=?";
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement ps = JdbcUtil.getPs(connection, sql,managerUser.getPassWord(), managerUser.getId());
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.Close(null, ps, connection);
		} 
		
	}


}
