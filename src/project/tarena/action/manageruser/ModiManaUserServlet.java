package project.tarena.action.manageruser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.tarena.entity.ManagerUser;
import project.tarena.service.ManagerUserService;
import project.tarena.service.impl.ManagerUserServiceImpl;

/**
 * 根据主键Id修改管理员用户的信息操作
 * @author Administrator
 *
 */
@WebServlet("/ModiManaUserServlet")
public class ModiManaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerUserService mus=new ManagerUserServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数
		String idStr=request.getParameter("id");
		//参数的有效性
		int id=0;
		try {
			id=Integer.parseInt(idStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//调用Service方法
		ManagerUser managerUser=mus.findById(id);
			if(managerUser!=null){
			request.setAttribute("manaUser2", managerUser);
			request.getRequestDispatcher("admin/admin_modi.jsp").forward(request, response);
			}
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
		String realName=request.getParameter("realName");
		String phoneNum=request.getParameter("phoneNum");
		String email=request.getParameter("email");
		//判断参数有效性
		String result=validate(realName,phoneNum,email);
		//如果判断参数有效性返回为空则进行下一步操作
		if(result==null){
			if(mus.findById(id)!=null){
				ManagerUser managerUser=new ManagerUser();
				//将获取的参数封装成一个对象
				managerUser.setRealName(realName);
				managerUser.setPhoneNum(phoneNum);
				managerUser.setEmail(email);
				managerUser.setId(id);
				//调用Service方法
				mus.modiManaUser(managerUser);
				//修改成功的话，跳转到修改界面并返回成功信息
				request.setAttribute("msg", "修改成功！");
				doGet(request, response);
			}
		}else{
//			request.setAttribute("id", id);
			request.setAttribute("msg", result);
			doGet(request, response);
			
//			String str = "ModiManaUserServlet?id="+id+"&msg="+"修改失败！";
//			response.sendRedirect(str);
//			request.getRequestDispatcher("ManaUserListServlet").forward(request, response);
		}
	}

	/**
	 * 判断传递过来的参数的有效性
	 * @param realName	真实姓名
	 * @param phoneNum	电话号码
	 * @param email		邮箱
	 * @return	如果有错误返回相应信息，否则返回null
	 */
	private String validate(String realName, String phoneNum, String email) {
		String result=null;
		if(realName==null||realName.length()==0){
			result="真实姓名不能为空！";
		}else if(realName.length()<2||realName.length()>10){
			result="真实姓名长度必须大于等于2小于等于10！";
		}else if(phoneNum==null||phoneNum.length()==0){
			result="电话号码不能为空！";
		}else if(phoneNum.length()!=11){
			result="电话号码必须为11位！";
		}else if(email==null||email.length()==0){
			result="邮箱不能为空！";
		}
		
		return result;
	}

}
