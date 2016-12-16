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
		//����sql���
		String sql="SELECT * FROM manager_user WHERE managerName=?";
		//�õ����ݿ�����
		Connection connection=JdbcUtil.getConnection();
		//�õ�PreparedStatement����
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, name);
		ResultSet rs=null;
		ManagerUser manageruser=null;
		try {
			//ִ�����
			rs=ps.executeQuery();
			//�����������ؽ����
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
		//����sql���
				String sql="SELECT * FROM manager_user WHERE id=?";
				//�õ����ݿ�����
				Connection connection=JdbcUtil.getConnection();
				//�õ�PreparedStatement����
				PreparedStatement ps=JdbcUtil.getPs(connection, sql, id);
				ResultSet rs=null;
				ManagerUser manageruser=null;
				try {
					//ִ�����
					rs=ps.executeQuery();
					//�����������ؽ����
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
		//����sql���
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT * FROM manager_user");
		//�ж�manaName��ֵʱ������sql���ƴ�ӽ��й���Ա�ʺ�ģ����ѯ
		//true��ʾ��Ҫ���ݹ���Ա�ʺ�ģ����ѯ��false��ʾ����Ҫ
		boolean flag=false;
		if(manaName!=null&&manaName.trim().length()!=0){
			sql.append(" where managerName like ?");
			flag=true;
		}
		//����connection����
		Connection connection=JdbcUtil.getConnection();
		//�õ�PreparedStatement����
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
			//ִ�����
			rs=ps.executeQuery();
			//������
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
		//����sql���
		String sql="insert into manager_user(realName,managerName,role,authorization_date,password,phoneNum,email) values(?,?,?,?,?,?,?)";
		//�õ�����
		Connection connection=JdbcUtil.getConnection();
		//�õ�preparedstatement����
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, manaUser.getRealName(),manaUser.getManagerName(),manaUser.getRole(),
				manaUser.getAuthorization_date(),manaUser.getPassWord(),manaUser.getPhoneNum(),manaUser.getEmail());
		try {
			//ִ�����
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(null, ps, connection);
		}
		
	}

	@Override
	public void delManaUser(int id) {
		//����sql���
		String sql="delete from manager_user where id=?";
		//�õ�����
		Connection connection=JdbcUtil.getConnection();
		//�õ�PreparedStatement����
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, id);
		try {
			//ִ�����
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(null, ps, connection);
		}
		
	}

	@Override
	public void resetManaUserpwd(int id) {
		//����sql���
		String sql="update manager_user set passWord=123456 where id=?";
		//���Connection����
		Connection connection=JdbcUtil.getConnection();
		//���PreparedStatement����
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, id);
		try {
			//ִ�����
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.Close(null, ps, connection);
		}
		
	}

	@Override
	public void modiManaUser(ManagerUser managerUser) {
		//����Sql���
		String sql="update manager_user set realName=?,phoneNum=?,email=? where id=?";
		//��ȡConnection����
		Connection connection=JdbcUtil.getConnection();
		//��ȡPreparedStatement����
		PreparedStatement ps=JdbcUtil.getPs(connection, sql, managerUser.getRealName(),managerUser.getPhoneNum(),managerUser.getEmail(),managerUser.getId());
		try {
			//ִ�����
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
