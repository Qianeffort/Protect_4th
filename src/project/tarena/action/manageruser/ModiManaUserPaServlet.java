package project.tarena.action.manageruser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.tarena.entity.ManagerUser;
import project.tarena.service.ManagerUserService;
import project.tarena.service.impl.ManagerUserServiceImpl;

/**
 * 根据主键Id修改管理员用户的密码操作
 * @author Administrator
 *
 */
@WebServlet(name = "ModiManaUserPaServlet", urlPatterns = { "/ModiManaUserPaServlet" })
public class ModiManaUserPaServlet extends HttpServlet {
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
		//从Sesstion中接收参数
		HttpSession session=request.getSession();
		ManagerUser managerUser=(ManagerUser)session.getAttribute("managerUser");
		int id=managerUser.getId();
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		String repeatpassword =request.getParameter("repeatpassword");
		//判断参数有效性
		String result=validate(oldpassword,newpassword,repeatpassword);
		if(result==null){
			ManagerUser manaUser=mus.findById(id);
			if(manaUser!=null){
				if(oldpassword.equals(manaUser.getPassWord())){
					//将获取的参数封装成一个对象
					manaUser.setPassWord(repeatpassword);
					//调用Service方法
					mus.modiPassWord(manaUser);
					//修改成功的话，跳转到修改密码界面并返回成功信息
					request.setAttribute("msg", "修改密码成功！");
					request.getRequestDispatcher("user/user_modi_pwd.jsp").forward(request, response);
				}else{
					request.setAttribute("msg", "旧密码输入错误！");
					request.getRequestDispatcher("user/user_modi_pwd.jsp").forward(request, response);
				}
				
			}
		}else{
			request.setAttribute("msg", result);
			request.getRequestDispatcher("user/user_modi_pwd.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * 判断相应参数的有效性，如果都成立返回null  如果有任何一种情况不成立，返回相应错误信息
	 * @param oldpassword	旧密码
	 * @param password		新密码
	 * @param repeatpassword	重复新密码
	 * @return
	 */
	private String validate(String oldpassword, String newpassword, String repeatpassword) {
		String result=null;
		System.out.println(oldpassword+"\t"+newpassword+"\t"+repeatpassword);
		if(oldpassword==null||oldpassword.length()==0){
			result="旧密码不能为空！";
		}else if(oldpassword.length()<6||oldpassword.length()>30){
			result="旧密码长度必须大于等于6小于等于30!";
		}else if(newpassword==null||newpassword.length()==0){
			result="新密码不能为空！";
		}else if(newpassword.length()<6||newpassword.length()>30){
			result="新密码长度必须大于等于6小于等于30!";
		}else if(repeatpassword==null||repeatpassword.length()==0){
			result="重复密码不能为空！";
		}else if(repeatpassword.length()<6||repeatpassword.length()>30){
			result="重复密码长度必须大于等于6小于等于30!";
		}else if(!(newpassword.equals(repeatpassword))){
			result="两次新密码输入不一致！";
		}
		return result;
	}

}
