package project.tarena.action.manageruser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �������ͼƬ��������Ϊ��֤��
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 3038623696184546092L;

	public static final int WIDTH = 120;// ���ɵ�ͼƬ�Ŀ��
	public static final int HEIGHT = 30;// ���ɵ�ͼƬ�ĸ߶�

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String createTypeFlag = request.getParameter("createTypeFlag");// ���տͻ��˴��ݵ�createTypeFlag��ʶ
		// 1.���ڴ��д���һ��ͼƬ
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2.�õ�ͼƬ
		Graphics g = bi.getGraphics();
		// 3.����ͼƬ�ı�Ӱɫ
		setBackGround(g);
		// 4.����ͼƬ�ı߿�
		setBorder(g);
		// 5.��ͼƬ�ϻ�������
		drawRandomLine(g);
		// 6.д��ͼƬ�������
		String random = drawRandomNum((Graphics2D) g, createTypeFlag);// ���ݿͻ��˴��ݵ�createTypeFlag��ʶ������֤��ͼƬ
		// 7.�����������session��
		request.getSession().setAttribute("checkcode", random);
		// 8.������Ӧͷ֪ͨ�������ͼƬ����ʽ��
		response.setContentType("image/jpeg");// ��ͬ��response.setHeader("Content-Type",
												// "image/jpeg");
		// 9.������Ӧͷ�����������Ҫ����
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 10.��ͼƬд�������
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}

	/**
	 * ����ͼƬ�ı���ɫ
	 * 
	 * @param g
	 */
	private void setBackGround(Graphics g) {
		// ������ɫ
		g.setColor(Color.WHITE);
		// �������
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * ����ͼƬ�ı߿�
	 * 
	 * @param g
	 */
	private void setBorder(Graphics g) {
		// ���ñ߿���ɫ
		g.setColor(Color.BLUE);
		// �߿�����
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	/**
	 * ��ͼƬ�ϻ��������
	 * 
	 * @param g
	 */
	private void drawRandomLine(Graphics g) {
		// ������ɫ
		g.setColor(Color.GREEN);
		// ������������������
		for (int i = 0; i < 5; i++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * ������ַ�
	 * 
	 * @param g
	 * @param createTypeFlag
	 * @return String... createTypeFlag�ǿɱ������
	 *         Java1.5�����������ԣ��ɱ�����������ڲ���������ȷ��������ȷ���������java�ѿɱ�����������鴦��ע�⣺�ɱ��������λ�����һ��
	 */
	private String drawRandomNum(Graphics2D g, String... createTypeFlag) {
		// ������ɫ
		g.setColor(Color.RED);
		// ��������
		g.setFont(new Font("����", Font.BOLD, 20));
		// ���ֺ���ĸ�����
		String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";

		return createRandomChar(g, baseNumLetter);

	}

	/**
	 * ��������ַ�
	 * 
	 * @param g
	 * @param baseChar
	 * @return ����ַ�
	 */
	private String createRandomChar(Graphics2D g, String baseChar) {
		StringBuffer sb = new StringBuffer();
		int x = 5;
		String ch = "";
		// ��������
		for (int i = 0; i < 4; i++) {
			// ����������ת�Ƕ�
			int degree = new Random().nextInt() % 30;
			ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
			sb.append(ch);
			// ����Ƕ�
			g.rotate(degree * Math.PI / 180, x, 20);
			g.drawString(ch, x, 20);
			// ����Ƕ�
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
		return sb.toString();
	}
}