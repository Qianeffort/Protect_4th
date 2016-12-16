package project.tarena.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	private final static String FULL_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * ���õ��ĵ�ǰ�������ַ���  ת����ʽΪyyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static String getCurrentTime(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat(FULL_FORMAT);
		return sdf.format(calendar.getTime());
	}
}
