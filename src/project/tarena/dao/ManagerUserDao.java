package project.tarena.dao;

import java.util.List;
import project.tarena.entity.ManagerUser;

public interface ManagerUserDao {

	/**
	 * �����û��������û�������Ϣ
	 * @param name	�û���
	 * @return		������ڷ���ManagerUser���󣬷��򷵻�null
	 */
	public ManagerUser findByName(String name);
	
	/**
	 * ��������ID��ѯ�û�������Ϣ
	 * @param id	����ID
	 * @return		ManagerUser����
	 */
	public ManagerUser findById(int id);
	/**
	 * ���ݹ���Ա�ʺ�ģ����ѯ����Ա�б���Ϣ
	 * @param manaName	����Ա�ʺ�
	 * @return	List����
	 */
	public List<ManagerUser> findManaUserList(String manaName);
	
	/**
	 * �����ݿ���ӹ���Ա
	 * @param manaUser  ����Ա����
	 */
	public void addManaUser(ManagerUser manaUser); 
	/**
	 * ��������Idɾ������Ա�û�������Ϣ
	 * @param id	����ID
	 */
	public void delManaUser(int id);
	/**
	 * ��������Id�޸ĵ�ǰ��¼����Ա�û���Ϣ
	 * @param managerUser
	 */
	public void modiManaUser(ManagerUser managerUser);
	/**
	 * ��������id���ù���Ա�û������� Ĭ��123456
	 * @param id	����Id
	 */
	public void resetManaUserpwd(int id);
	/**
	 * ��������Id�޸ĵ�ǰ��¼����Ա�û�����
	 * @param id
	 */
	public void modiPassWord(ManagerUser managerUser);
	/**
	 * �޸Ĺ���Ա�û�������¼ʱ��
	 * @param user
	 */
	public void updateTime(ManagerUser managerUser); 
}
