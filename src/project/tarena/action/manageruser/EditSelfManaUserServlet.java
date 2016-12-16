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
 * 当前登录的超级管理员对自我的信息进行修改的操作类
 * @author Administrator
 *
 */
@WebServlet("/EditSelfManaUserServlet")
public class EditSelfManaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerUserService mus=new ManagerUserServiceImpl();
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从Sesstion中接收参数
		HttpSession session=request.getSession();
		ManagerUser managerUser=(ManagerUser)session.getAttribute("managerUser");
		int id=managerUser.getId();
		//调用Service方法
		ManagerUser manaUser=mus.findById(id);
		if(manaUser!=null){
			request.setAttribute("manaUser", manaUser);
			request.getRequestDispatcher("user/user_info.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从Sesstion中接收参数
		HttpSession session=request.getSession();
		ManagerUser managerUser=(ManagerUser)session.getAttribute("managerUser");
		int id=managerUser.getId();
		String realName=request.getParameter("realName");
		String phoneNum=request.getParameter("phoneNum");
		String email=request.getParameter("email");
		//判断参数有效性
		String result=validate(realName,phoneNum,email);
		//如果判断参数有效性返回为空则进行下一步操作
		if(result==null){
			if(mus.findById(id)!=null){
				ManagerUser manaUser=new ManagerUser();
				//将获取的参数封装成一个对象
				manaUser.setRealName(realName);
				manaUser.setPhoneNum(phoneNum);
				manaUser.setEmail(email);
				manaUser.setId(id);
				//调用Service方法
				mus.modiManaUser(manaUser);
				//修改成功的话，跳转到修改界面并返回成功信息
				request.setAttribute("msg", "修改成功！");
				doGet(request, response);
			}
		}else{
			request.setAttribute("msg", result);
			doGet(request, response);
		}
	}
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
