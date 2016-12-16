package project.tarena.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Properties������
 * @author Administrator
 *
 */
public class PropertiesUtil {
/**
 * ���ݴ����keyֵ�õ���Ӧ��ֵ
 * @param key	keyֵ
 * @return	������ڷ���result�����򷵻�null
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
