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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//传递参数
		String managerName=request.getParameter("managerName");
		String passWord=request.getParameter("passWord");
		//验证参数的有效性
		String result=validate(managerName,passWord);
		if(result==null){
			//接收客户端浏览器提交上来的验证码
			String userCheckcode=request.getParameter("validateCode");
			//从服务器端的session中获取验证码
			String serverCheckcode=(String)request.getSession().getAttribute("checkcode");
			//将客户端验证码和服务器端验证码相比较，如果相等，则表示验证通过，否则验证不通过
			if(userCheckcode.equalsIgnoreCase(serverCheckcode)){
				//调用Service方法
				ManagerUserService mus=new ManagerUserServiceImpl();
				//创建Session
				HttpSession session=request.getSession();
				ManagerUser manaUser=mus.getLastLoginTime(managerName);
				session.setAttribute("manaUserTime", manaUser.getLastlogintime());
				ManagerUser managerUser=mus.login(managerName, passWord);
				if(managerUser!=null){
					
					//设置登录成功的状态
					session.setAttribute("managerUser", managerUser);
					//进入到index.jsp
					response.sendRedirect("index.jsp");
				}else{
						request.setAttribute("error", "用户名或密码输入错误");
						request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("codeerror", "验证码输入错误！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}else{
			//跳转到登录界面
			request.setAttribute("result", result);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	private String validate(String managerName, String passWord) {
		String result=null;
		if(managerName==null||managerName.length()==0){
			result="用户名不能为空！";
		}else if(managerName.length()<5||managerName.length()>30){
			result="帐号长度必须大于等于5小于等于30";
		}else if(passWord==null||passWord.length()==0){
			result="密码不能为空！";
		}else if(passWord.length()<6||passWord.length()>30){
			result="密码长度必须大于等于6小于等于30";
		}
		return result;
	}

}
