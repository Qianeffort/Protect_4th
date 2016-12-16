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
 * ��������Id�޸Ĺ���Ա�û�����Ϣ����
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
		//���ղ���
		String idStr=request.getParameter("id");
		//��������Ч��
		int id=0;
		try {
			id=Integer.parseInt(idStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//����Service����
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
		//���ղ���
		String idStr=request.getParameter("id");
		//��������Ч��
		int id=0;
		try {
			id=Integer.parseInt(idStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String realName=request.getParameter("realName");
		String phoneNum=request.getParameter("phoneNum");
		String email=request.getParameter("email");
		//�жϲ�����Ч��
		String result=validate(realName,phoneNum,email);
		//����жϲ�����Ч�Է���Ϊ���������һ������
		if(result==null){
			if(mus.findById(id)!=null){
				ManagerUser managerUser=new ManagerUser();
				//����ȡ�Ĳ�����װ��һ������
				managerUser.setRealName(realName);
				managerUser.setPhoneNum(phoneNum);
				managerUser.setEmail(email);
				managerUser.setId(id);
				//����Service����
				mus.modiManaUser(managerUser);
				//�޸ĳɹ��Ļ�����ת���޸Ľ��沢���سɹ���Ϣ
				request.setAttribute("msg", "�޸ĳɹ���");
				doGet(request, response);
			}
		}else{
//			request.setAttribute("id", id);
			request.setAttribute("msg", result);
			doGet(request, response);
			
//			String str = "ModiManaUserServlet?id="+id+"&msg="+"�޸�ʧ�ܣ�";
//			response.sendRedirect(str);
//			request.getRequestDispatcher("ManaUserListServlet").forward(request, response);
		}
	}

	/**
	 * �жϴ��ݹ����Ĳ�������Ч��
	 * @param realName	��ʵ����
	 * @param phoneNum	�绰����
	 * @param email		����
	 * @return	����д��󷵻���Ӧ��Ϣ�����򷵻�null
	 */
	private String validate(String realName, String phoneNum, String email) {
		String result=null;
		if(realName==null||realName.length()==0){
			result="��ʵ��������Ϊ�գ�";
		}else if(realName.length()<2||realName.length()>10){
			result="��ʵ�������ȱ�����ڵ���2С�ڵ���10��";
		}else if(phoneNum==null||phoneNum.length()==0){
			result="�绰���벻��Ϊ�գ�";
		}else if(phoneNum.length()!=11){
			result="�绰�������Ϊ11λ��";
		}else if(email==null||email.length()==0){
			result="���䲻��Ϊ�գ�";
		}
		
		return result;
	}

}
