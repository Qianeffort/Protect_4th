package project.tarena.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Dao层的工厂模式类
 * 作用：解决dao层和service层的耦合度
 * @author Administrator
 *
 */
public class DaoFactory {

	/*将dao层接口和dao层的实现类在配置文件factory.properties中定义，
	 *以dao接口名作为关键字，以dao实现类的全名(包名+类名)作为关键字的值
	 *好处：
	 *使用这样配置的好处在于以后对于UserDao接口，如果我们想更换实现类，
	 *只要改动配置文件即可，代码中完全不需要修改。
	 */
	
	//单例模式-->私有的-->其他外类使用它不能通过new的方式，只能通过调用它的方法来使用
	private static DaoFactory instance = new DaoFactory();
	private Properties properties=new Properties();
	
	private DaoFactory(){
		//读取配置文件装载进输入流
		InputStream is=DaoFactory.class.getClassLoader().getResourceAsStream("factory.properties");
		try {
			//将配置文件封装进Properties对象中
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public <T>T createDao(Class<T> clazz){
		//获取配置文件中的接口关键字
		String interfaceName=clazz.getSimpleName();
		//根据接口关键字获取配置文件中的具体实现类的名称
		String className=properties.getProperty(interfaceName);
		try {
			T bean=(T)Class.forName(className).newInstance();
			return bean;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
