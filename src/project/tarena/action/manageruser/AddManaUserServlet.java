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
 * �����ݿ���������Ա��Servlet
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
		//���ղ���
		String realName=request.getParameter("realName");
		String manaName=request.getParameter("manaName");
		String role=request.getParameter("role");
		String passWord1=request.getParameter("passWord1");
		String passWord2=request.getParameter("passWord2");
		String phoneNum=request.getParameter("phoneNum");
		String email=request.getParameter("email");
		//�жϲ�����Ч��
		String result=validate(realName,manaName,role,passWord1,passWord2,phoneNum,email);
		//�����������Ϊnullִ����һ��
		if(result==null){
			//�ж����������Ƿ�һ�£�һ�����ò���ֵ����service���������򷵻����������Ϣ
			if(passWord1.equals(passWord2)){
				ManagerUser manageruser=new ManagerUser();
				manageruser.setRealName(realName);
				manageruser.setManagerName(manaName);
				manageruser.setRole(role);
				manageruser.setPassWord(passWord1);
				manageruser.setPhoneNum(phoneNum);
				manageruser.setEmail(email);
				manageruser.setAuthorization_date(DateUtil.getCurrentTime());
				//����Service����
				mus.addManaUser(manageruser);
				//�����ɹ��Ļ�����ת����������Ա����
				request.setAttribute("msg", "�����ɹ���");
				request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "�����������벻һ�£�");
				request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
			}
		}else{
			//����������ֵʱ����ʾ��֤��ͨ��������Ӧ������Ϣ���ص�ҳ�沢��ת����������Ա����
			request.setAttribute("msg", result);
			request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
		}
	}

	/**
	 * �жϴ��ݹ����Ĳ�����Ч��
	 * @param realName	��ʵ����
	 * @param manaName	����Ա�ʺ�
	 * @param role		��ɫ
	 * @param passWord1	����
	 * @param passWord2	�ظ�����
	 * @param phoneNum	�绰����
	 * @param email		����
	 * @return		����д��󷵻���Ӧ��Ϣ�����򷵻�null
	 */
	private String validate(String realName, String manaName, String role, String passWord1, String passWord2,
			String phoneNum, String email) {
		String result=null;
		if(realName==null||realName.length()==0){
			result="��ʵ��������Ϊ�գ�";
		}else if(realName.length()<2||realName.length()>10){
			result="��ʵ�������ȱ�����ڵ���2С�ڵ���10��";
		}else if(manaName==null||manaName.length()==0){
			result="�û�������Ϊ�գ�";
		}else if(manaName.length()<5||manaName.length()>30){
			result="�ʺų��ȱ�����ڵ���5С�ڵ���30��";
		}else if(mus.findByName(manaName)!=null){
			result="�� "+manaName+" ��"+" �û����Ѵ��ڣ���������д��";
		}else if(passWord1==null||passWord1.length()==0){
			result="���벻��Ϊ�գ�";
		}else if(passWord1.length()<6||passWord1.length()>30){
			result="���볤�ȱ�����ڵ���6С�ڵ���30��";
		}else if(passWord2==null||passWord2.length()==0){
			result="���벻��Ϊ�գ�";
		}else if(passWord2.length()<6||passWord2.length()>30){
			result="���볤�ȱ�����ڵ���6С�ڵ���30��";
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
