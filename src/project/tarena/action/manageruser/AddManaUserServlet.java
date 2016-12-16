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
import project.tarena.util.DateUtil;

/**
 * 向数据库新增管理员的Servlet
 * @author Administrator
 *
 */
@WebServlet("/AddManaUserServlet")
public class AddManaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ManagerUserService mus=new ManagerUserServiceImpl();
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
		String realName=request.getParameter("realName");
		String manaName=request.getParameter("manaName");
		String role=request.getParameter("role");
		String passWord1=request.getParameter("passWord1");
		String passWord2=request.getParameter("passWord2");
		String phoneNum=request.getParameter("phoneNum");
		String email=request.getParameter("email");
		//判断参数有效性
		String result=validate(realName,manaName,role,passWord1,passWord2,phoneNum,email);
		//如果参数返回为null执行下一步
		if(result==null){
			//判断两次密码是否一致，一致设置参数值调用service方法，否则返回密码错误信息
			if(passWord1.equals(passWord2)){
				ManagerUser manageruser=new ManagerUser();
				manageruser.setRealName(realName);
				manageruser.setManagerName(manaName);
				manageruser.setRole(role);
				manageruser.setPassWord(passWord1);
				manageruser.setPhoneNum(phoneNum);
				manageruser.setEmail(email);
				manageruser.setAuthorization_date(DateUtil.getCurrentTime());
				//调用Service方法
				mus.addManaUser(manageruser);
				//新增成功的话，跳转到新增管理员界面
				request.setAttribute("msg", "新增成功！");
				request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "两次密码输入不一致！");
				request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
			}
		}else{
			//参数返回有值时，表示验证不通过，将相应错误信息返回到页面并跳转到新增管理员界面
			request.setAttribute("msg", result);
			request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
		}
	}

	/**
	 * 判断传递过来的参数有效性
	 * @param realName	真实姓名
	 * @param manaName	管理员帐号
	 * @param role		角色
	 * @param passWord1	密码
	 * @param passWord2	重复密码
	 * @param phoneNum	电话号码
	 * @param email		邮箱
	 * @return		如果有错误返回相应信息，否则返回null
	 */
	private String validate(String realName, String manaName, String role, String passWord1, String passWord2,
			String phoneNum, String email) {
		String result=null;
		if(realName==null||realName.length()==0){
			result="真实姓名不能为空！";
		}else if(realName.length()<2||realName.length()>10){
			result="真实姓名长度必须大于等于2小于等于10！";
		}else if(manaName==null||manaName.length()==0){
			result="用户名不能为空！";
		}else if(manaName.length()<5||manaName.length()>30){
			result="帐号长度必须大于等于5小于等于30！";
		}else if(mus.findByName(manaName)!=null){
			result="“ "+manaName+" ”"+" 用户名已存在，请重新填写！";
		}else if(passWord1==null||passWord1.length()==0){
			result="密码不能为空！";
		}else if(passWord1.length()<6||passWord1.length()>30){
			result="密码长度必须大于等于6小于等于30！";
		}else if(passWord2==null||passWord2.length()==0){
			result="密码不能为空！";
		}else if(passWord2.length()<6||passWord2.length()>30){
			result="密码长度必须大于等于6小于等于30！";
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
