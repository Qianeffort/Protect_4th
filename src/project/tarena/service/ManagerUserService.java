package project.tarena.service;

import java.util.List;

import project.tarena.entity.ManagerUser;

/**
 * �û�����Service��
 * @author Administrator
 *
 */
public interface ManagerUserService {

	/**
	 * ���ݹ���Ա�ʺŲ����û���Ϣ
	 * @param name	�û���
	 * @return	���������Ӧ�û�������Ӧ��Ϣ�����򷵻�null
	 */
	public ManagerUser findByName(String name);
	/**
	 * ����Ա��¼
	 * @param managerName	����Ա�ʺ�
	 * @param passWord		����
	 * @return		true��ʾ��¼�ɹ�  false��ʾ��¼ʧ��
	 */
	public ManagerUser login(String managerName,String passWord);
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
	 * ��������ID��ѯ����Ա������Ϣ
	 * @param id	����ID
	 * @return		ManagerUser����
	 */
	public ManagerUser findById(int id);
	/**
	 * ��������Id�޸ĵ�ǰ��¼����Ա�û���Ϣ
	 * @param managerUser
	 */
	public void modiManaUser(ManagerUser managerUser);
	/**
	 * ��������Id�޸ĵ�ǰ��¼����Ա�û�����
	 * @param managerUser
	 */
	public void modiPassWord(ManagerUser managerUser);
	/**
	 * ��������id���ù���Ա�û�������  Ĭ��123456
	 * @param id	����Id
	 */
	public void resetManaUserpwd(int id);
	/**
	 * �����û�����ȡ��ǰ�û������ϴε�¼ʱ��
	 * @param managerName
	 * @return
	 */
	public ManagerUser getLastLoginTime(String managerName);
}
