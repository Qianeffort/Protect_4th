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
		
		//���ݲ���
		String managerName=request.getParameter("managerName");
		String passWord=request.getParameter("passWord");
		//��֤��������Ч��
		String result=validate(managerName,passWord);
		if(result==null){
			//���տͻ���������ύ��������֤��
			String userCheckcode=request.getParameter("validateCode");
			//�ӷ������˵�session�л�ȡ��֤��
			String serverCheckcode=(String)request.getSession().getAttribute("checkcode");
			//���ͻ�����֤��ͷ���������֤����Ƚϣ������ȣ����ʾ��֤ͨ����������֤��ͨ��
			if(userCheckcode.equalsIgnoreCase(serverCheckcode)){
				//����Service����
				ManagerUserService mus=new ManagerUserServiceImpl();
				//����Session
				HttpSession session=request.getSession();
				ManagerUser manaUser=mus.getLastLoginTime(managerName);
				session.setAttribute("manaUserTime", manaUser.getLastlogintime());
				ManagerUser managerUser=mus.login(managerName, passWord);
				if(managerUser!=null){
					
					//���õ�¼�ɹ���״̬
					session.setAttribute("managerUser", managerUser);
					//���뵽index.jsp
					response.sendRedirect("index.jsp");
				}else{
						request.setAttribute("error", "�û����������������");
						request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("codeerror", "��֤���������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}else{
			//��ת����¼����
			request.setAttribute("result", result);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	private String validate(String managerName, String passWord) {
		String result=null;
		if(managerName==null||managerName.length()==0){
			result="�û�������Ϊ�գ�";
		}else if(managerName.length()<5||managerName.length()>30){
			result="�ʺų��ȱ�����ڵ���5С�ڵ���30";
		}else if(passWord==null||passWord.length()==0){
			result="���벻��Ϊ�գ�";
		}else if(passWord.length()<6||passWord.length()>30){
			result="���볤�ȱ�����ڵ���6С�ڵ���30";
		}
		return result;
	}

}
