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
 * ��ǰ��¼�ĳ�������Ա�����ҵ���Ϣ�����޸ĵĲ�����
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
		//��Sesstion�н��ղ���
		HttpSession session=request.getSession();
		ManagerUser managerUser=(ManagerUser)session.getAttribute("managerUser");
		int id=managerUser.getId();
		//����Service����
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
		//��Sesstion�н��ղ���
		HttpSession session=request.getSession();
		ManagerUser managerUser=(ManagerUser)session.getAttribute("managerUser");
		int id=managerUser.getId();
		String realName=request.getParameter("realName");
		String phoneNum=request.getParameter("phoneNum");
		String email=request.getParameter("email");
		//�жϲ�����Ч��
		String result=validate(realName,phoneNum,email);
		//����жϲ�����Ч�Է���Ϊ���������һ������
		if(result==null){
			if(mus.findById(id)!=null){
				ManagerUser manaUser=new ManagerUser();
				//����ȡ�Ĳ�����װ��һ������
				manaUser.setRealName(realName);
				manaUser.setPhoneNum(phoneNum);
				manaUser.setEmail(email);
				manaUser.setId(id);
				//����Service����
				mus.modiManaUser(manaUser);
				//�޸ĳɹ��Ļ�����ת���޸Ľ��沢���سɹ���Ϣ
				request.setAttribute("msg", "�޸ĳɹ���");
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
