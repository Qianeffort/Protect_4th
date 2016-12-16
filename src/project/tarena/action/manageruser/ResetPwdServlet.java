package project.tarena.action.manageruser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.tarena.service.ManagerUserService;
import project.tarena.service.impl.ManagerUserServiceImpl;

/**
 * 根据主键id重置管理员用户密码的操作
 * @author Administrator
 *
 */
@WebServlet("/ResetPwdServlet")
public class ResetPwdServlet extends HttpServlet {
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
		String idStr=request.getParameter("id");
		//参数的有效性
		int id=0;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ManagerUserService mus=new ManagerUserServiceImpl();
		//验证根据id查询的用户是否存在，存在调用Service方法
		if(mus.findById(id)!=null){
			//调用Service方法
			mus.resetManaUserpwd(id);
			request.setAttribute("msg", "重置密码成功！");
			request.getRequestDispatcher("ManaUserListServlet").forward(request, response);
		}
	}
}
