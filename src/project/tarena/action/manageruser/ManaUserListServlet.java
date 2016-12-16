package project.tarena.action.manageruser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.tarena.entity.ManagerUser;
import project.tarena.service.ManagerUserService;
import project.tarena.service.impl.ManagerUserServiceImpl;

/**
 * 管理员列表信息
 * @author Administrator
 *
 */
@WebServlet("/ManaUserListServlet")
public class ManaUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数
		String manaName=request.getParameter("manaName");
		//调用Service方法
		ManagerUserService mus=new ManagerUserServiceImpl();
		List<ManagerUser> list=mus.findManaUserList(manaName);
		//跳转
		request.setAttribute("list", list);
		request.getRequestDispatcher("admin/admin_list.jsp").forward(request, response);
		
		
	}

}
