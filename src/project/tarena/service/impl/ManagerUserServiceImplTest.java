package project.tarena.service.impl;


import java.util.List;

import org.junit.Test;

import project.tarena.entity.ManagerUser;

public class ManagerUserServiceImplTest {

	@Test
	public void testLogin() {
		ManagerUserServiceImpl ms=new ManagerUserServiceImpl();
		List<ManagerUser> list=ms.findManaUserList(null);
		System.out.println(list);
		
	}

}
