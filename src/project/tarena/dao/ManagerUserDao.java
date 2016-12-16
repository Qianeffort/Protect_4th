package project.tarena.dao;

import java.util.List;
import project.tarena.entity.ManagerUser;

public interface ManagerUserDao {

	/**
	 * 根据用户名查找用户所有信息
	 * @param name	用户名
	 * @return		如果存在返回ManagerUser对象，否则返回null
	 */
	public ManagerUser findByName(String name);
	
	/**
	 * 根据主键ID查询用户所有信息
	 * @param id	主键ID
	 * @return		ManagerUser对象
	 */
	public ManagerUser findById(int id);
	/**
	 * 根据管理员帐号模糊查询管理员列表信息
	 * @param manaName	管理员帐号
	 * @return	List对象
	 */
	public List<ManagerUser> findManaUserList(String manaName);
	
	/**
	 * 向数据库添加管理员
	 * @param manaUser  管理员对象
	 */
	public void addManaUser(ManagerUser manaUser); 
	/**
	 * 根据主键Id删除管理员用户所有信息
	 * @param id	主键ID
	 */
	public void delManaUser(int id);
	/**
	 * 根据主键Id修改当前登录管理员用户信息
	 * @param managerUser
	 */
	public void modiManaUser(ManagerUser managerUser);
	/**
	 * 根据主键id重置管理员用户的密码 默认123456
	 * @param id	主键Id
	 */
	public void resetManaUserpwd(int id);
	/**
	 * 根据主键Id修改当前登录管理员用户密码
	 * @param id
	 */
	public void modiPassWord(ManagerUser managerUser);
	/**
	 * 修改管理员用户的最后登录时间
	 * @param user
	 */
	public void updateTime(ManagerUser managerUser); 
}
