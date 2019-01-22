package org.thevil.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期格式转换工具
 * @author Kevin zhaosheji
 * @date 2019年1月22日
 */
public class DateUtils {
	
	/**
	 * 
	 * @Description: TODO(将字符串格式的日期，转为Date)
	 * @author Kevin zhaosheji
	 * @date 2019年1月22日
	 */
	public static Date parse(String date) {
	    	if(date!=null) {
	    		try {
	    			if(date.contains("-")) {
						if(date.contains(":") && date.lastIndexOf("Z")>0) {
							date = date.replace("Z", " UTC");
							return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(date);
						}else if(date.contains(":")){
							return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date);
						}else {
							return new SimpleDateFormat("yyyy-MM-dd").parse(date);
						}
					}
					if(date.contains("/")) {
						if(date.contains(":") && date.lastIndexOf("Z")>0) {
							date = date.replace("Z", " UTC");
							return new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss.SSS Z").parse(date);
						}else  if(date.contains(":")) {
							return new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss").parse(date);
						}else {
							return new SimpleDateFormat("yyyy/MM/dd").parse(date);
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
	    	}
	        return null;
	    }
}

