package project.tarena.action.manageruser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.tarena.service.ManagerUserService;
import project.tarena.service.impl.ManagerUserServiceImpl;

/**
 * ��������Idɾ������Ա�û���Ϣ��Servlet
 * @author Administrator
 *
 */
@WebServlet("/DelManaUserServlet")
public class DelManaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
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
		String idStr=request.getParameter("id");
		//��������Ч��
		int id=0;
		try {
			id=Integer.parseInt(idStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
		ManagerUserService mus=new ManagerUserServiceImpl();
		//��֤������Ч��
		if(mus.findById(id)!=null){
			//����Service
			mus.delManaUser(id);
			//�ɹ���ת������Ա�б����
			response.sendRedirect("ManaUserListServlet");
		}else{
			//ʧ����ʾ��Ӧ��Ϣ����ת������Ա�б����
			request.setAttribute("msg", "���û������ڣ�");
			request.getRequestDispatcher("ManaUserListServlet").forward(request, response);
		}
	}

}
