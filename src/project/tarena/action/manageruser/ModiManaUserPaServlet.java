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
 * ��������Id�޸Ĺ���Ա�û����������
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
		//��Sesstion�н��ղ���
		HttpSession session=request.getSession();
		ManagerUser managerUser=(ManagerUser)session.getAttribute("managerUser");
		int id=managerUser.getId();
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		String repeatpassword =request.getParameter("repeatpassword");
		//�жϲ�����Ч��
		String result=validate(oldpassword,newpassword,repeatpassword);
		if(result==null){
			ManagerUser manaUser=mus.findById(id);
			if(manaUser!=null){
				if(oldpassword.equals(manaUser.getPassWord())){
					//����ȡ�Ĳ�����װ��һ������
					manaUser.setPassWord(repeatpassword);
					//����Service����
					mus.modiPassWord(manaUser);
					//�޸ĳɹ��Ļ�����ת���޸�������沢���سɹ���Ϣ
					request.setAttribute("msg", "�޸�����ɹ���");
					request.getRequestDispatcher("user/user_modi_pwd.jsp").forward(request, response);
				}else{
					request.setAttribute("msg", "�������������");
					request.getRequestDispatcher("user/user_modi_pwd.jsp").forward(request, response);
				}
				
			}
		}else{
			request.setAttribute("msg", result);
			request.getRequestDispatcher("user/user_modi_pwd.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * �ж���Ӧ��������Ч�ԣ��������������null  ������κ�һ�������������������Ӧ������Ϣ
	 * @param oldpassword	������
	 * @param password		������
	 * @param repeatpassword	�ظ�������
	 * @return
	 */
	private String validate(String oldpassword, String newpassword, String repeatpassword) {
		String result=null;
		System.out.println(oldpassword+"\t"+newpassword+"\t"+repeatpassword);
		if(oldpassword==null||oldpassword.length()==0){
			result="�����벻��Ϊ�գ�";
		}else if(oldpassword.length()<6||oldpassword.length()>30){
			result="�����볤�ȱ�����ڵ���6С�ڵ���30!";
		}else if(newpassword==null||newpassword.length()==0){
			result="�����벻��Ϊ�գ�";
		}else if(newpassword.length()<6||newpassword.length()>30){
			result="�����볤�ȱ�����ڵ���6С�ڵ���30!";
		}else if(repeatpassword==null||repeatpassword.length()==0){
			result="�ظ����벻��Ϊ�գ�";
		}else if(repeatpassword.length()<6||repeatpassword.length()>30){
			result="�ظ����볤�ȱ�����ڵ���6С�ڵ���30!";
		}else if(!(newpassword.equals(repeatpassword))){
			result="�������������벻һ�£�";
		}
		return result;
	}

}
