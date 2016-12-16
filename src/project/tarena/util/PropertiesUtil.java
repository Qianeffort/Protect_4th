package project.tarena.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Properties工具类
 * @author Administrator
 *
 */
public class PropertiesUtil {
/**
 * 根据传入的key值得到相应的值
 * @param key	key值
 * @return	如果存在返回result，否则返回null
 */
	public static String getValues(String key){
		Properties properties=new Properties();
		InputStream inputstream=null;
		String result=null;
		try {
			inputstream=PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			properties.load(inputstream);
			result=properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
