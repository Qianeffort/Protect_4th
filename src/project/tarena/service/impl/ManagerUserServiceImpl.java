package project.tarena.service.impl;

import java.util.List;


import project.tarena.dao.ManagerUserDao;
import project.tarena.entity.ManagerUser;
import project.tarena.factory.DaoFactory;
import project.tarena.service.ManagerUserService;
import project.tarena.util.DateUtil;

public class ManagerUserServiceImpl implements ManagerUserService {
	ManagerUserDao mud=DaoFactory.getInstance().createDao(ManagerUserDao.class);

	@Override
	public ManagerUser findByName(String name) {
		return mud.findByName(name);
	}

	@Override
	public ManagerUser login(String managerName, String passWord) {
		//根据用户名查找用户是否存在
		ManagerUser managerUser=mud.findByName(managerName);
		//如果存在进行密码判断
		if(managerUser!=null){
			if(managerUser.getPassWord().equals(passWord)){
				managerUser.setLastlogintime(DateUtil.getCurrentTime());
				mud.updateTime(managerUser);
			}
			return managerUser;
		}
		return null;
	}

	@Override
	public ManagerUser findById(int id) {
		return mud.findById(id);
	}

	@Override
	public List<ManagerUser> findManaUserList(String manaName) {
		return mud.findManaUserList(manaName);
	}

	@Override
	public void addManaUser(ManagerUser manaUser) {
		mud.addManaUser(manaUser);
	}

	@Override
	public void delManaUser(int id) {
		mud.delManaUser(id);
	}

	@Override
	public void resetManaUserpwd(int id) {
		mud.resetManaUserpwd(id);
	}

	@Override
	public void modiManaUser(ManagerUser managerUser) {
		mud.modiManaUser(managerUser);
	}

	@Override
	public void modiPassWord(ManagerUser managerUser) {
		mud.modiPassWord(managerUser);
	}

	@Override
	public ManagerUser getLastLoginTime(String managerName) {
		return mud.findByName(managerName);
	}
}
