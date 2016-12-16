package project.tarena.dao.impl;
import org.junit.Test;

import project.tarena.dao.ManagerUserDao;

public class ManagerUserDaoImplTest {

	@Test
	public void testFindByName() {
		ManagerUserDao mud=new ManagerUserDaoImpl();
		System.out.println(mud.findByName("admin"));
		System.out.println(mud.findByName("asdfg"));
	}

}
