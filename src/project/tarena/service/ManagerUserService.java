package project.tarena.service;

import java.util.List;

import project.tarena.entity.ManagerUser;

/**
 * 用户管理Service层
 * @author Administrator
 *
 */
public interface ManagerUserService {

	/**
	 * 根据管理员帐号查找用户信息
	 * @param name	用户名
	 * @return	如果存在相应用户返回相应信息，否则返回null
	 */
	public ManagerUser findByName(String name);
	/**
	 * 管理员登录
	 * @param managerName	管理员帐号
	 * @param passWord		密码
	 * @return		true表示登录成功  false表示登录失败
	 */
	public ManagerUser login(String managerName,String passWord);
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
	 * 根据主键ID查询管理员所有信息
	 * @param id	主键ID
	 * @return		ManagerUser对象
	 */
	public ManagerUser findById(int id);
	/**
	 * 根据主键Id修改当前登录管理员用户信息
	 * @param managerUser
	 */
	public void modiManaUser(ManagerUser managerUser);
	/**
	 * 根据主键Id修改当前登录管理员用户密码
	 * @param managerUser
	 */
	public void modiPassWord(ManagerUser managerUser);
	/**
	 * 根据主键id重置管理员用户的密码  默认123456
	 * @param id	主键Id
	 */
	public void resetManaUserpwd(int id);
	/**
	 * 根据用户名获取当前用户名的上次登录时间
	 * @param managerName
	 * @return
	 */
	public ManagerUser getLastLoginTime(String managerName);
}
