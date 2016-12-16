package project.tarena.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Dao��Ĺ���ģʽ��
 * ���ã����dao���service�����϶�
 * @author Administrator
 *
 */
public class DaoFactory {

	/*��dao��ӿں�dao���ʵ�����������ļ�factory.properties�ж��壬
	 *��dao�ӿ�����Ϊ�ؼ��֣���daoʵ�����ȫ��(����+����)��Ϊ�ؼ��ֵ�ֵ
	 *�ô���
	 *ʹ���������õĺô������Ժ����UserDao�ӿڣ�������������ʵ���࣬
	 *ֻҪ�Ķ������ļ����ɣ���������ȫ����Ҫ�޸ġ�
	 */
	
	//����ģʽ-->˽�е�-->��������ʹ��������ͨ��new�ķ�ʽ��ֻ��ͨ���������ķ�����ʹ��
	private static DaoFactory instance = new DaoFactory();
	private Properties properties=new Properties();
	
	private DaoFactory(){
		//��ȡ�����ļ�װ�ؽ�������
		InputStream is=DaoFactory.class.getClassLoader().getResourceAsStream("factory.properties");
		try {
			//�������ļ���װ��Properties������
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public <T>T createDao(Class<T> clazz){
		//��ȡ�����ļ��еĽӿڹؼ���
		String interfaceName=clazz.getSimpleName();
		//���ݽӿڹؼ��ֻ�ȡ�����ļ��еľ���ʵ���������
		String className=properties.getProperty(interfaceName);
		try {
			T bean=(T)Class.forName(className).newInstance();
			return bean;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
