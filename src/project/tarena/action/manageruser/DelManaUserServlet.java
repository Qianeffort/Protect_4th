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
 * 根据主键Id删除管理员用户信息的Servlet
 * @author Administrator
 *
 */
@WebServlet("/DelManaUserServlet")
public class DelManaUserServlet extends HttpServlet {
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
			id=Integer.parseInt(idStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
		ManagerUserService mus=new ManagerUserServiceImpl();
		//验证参数有效性
		if(mus.findById(id)!=null){
			//调用Service
			mus.delManaUser(id);
			//成功跳转到管理员列表界面
			response.sendRedirect("ManaUserListServlet");
		}else{
			//失败提示相应信息，跳转到管理员列表界面
			request.setAttribute("msg", "该用户不存在！");
			request.getRequestDispatcher("ManaUserListServlet").forward(request, response);
		}
	}

}
