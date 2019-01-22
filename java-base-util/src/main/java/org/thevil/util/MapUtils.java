
package org.thevil.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  Map工具类
 * @author Kevin zhaosheji
 * @date 2019年1月22日
 */
public class MapUtils extends HashMap<String, Object> {
    
    public static<T> T map2Entity(Map<String,Object> map,T t) throws Exception {
    	if(map!=null) {
    		Class<?> clazz = t.getClass();
        	for (Entry<String,Object> entry : map.entrySet()) {
    			String mapName = entry.getKey();
    			Object value = entry.getValue();
    			Field declaredField = null;
    			if(value!=null){
    				try {
    					declaredField = clazz.getDeclaredField(mapName);
    					if(declaredField!=null){
    						declaredField.setAccessible(true);
    						if(declaredField.getType() == String.class){
    							declaredField.set(t,value.toString());
    						}else if(declaredField.getType() == Date.class){
    							declaredField.set(t,DateUtils.parse(value.toString()));
    						}else if(declaredField.getType() == Timestamp.class){
    							declaredField.set(t,DateUtils.parse(value.toString()));
    						}else if(declaredField.getType() == Double.class){
    							declaredField.set(t,new Double(value.toString()));
    						}else if(declaredField.getType() == Float.class){
    							declaredField.set(t,new Float(value.toString()));
    						}else if(declaredField.getType() == Integer.class){
    							declaredField.set(t,new Integer(value.toString()));
    						}else if(declaredField.getType() == BigDecimal.class){
    							declaredField.set(t,new BigDecimal(value.toString()));
    						}
    					}
    				} catch (Exception e) {
    					throw new Exception("The "+value+ " of "  +mapName+" data format is "+" error.");
    				}
    			}
        	}
    	}
    	return t;
    }
   
    /**
     * 
     * @param t
     * @return
     */
    public static<T> Map<String,Object> entity2Map(T t) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	try {
			if(t!=null) {
				Class<?> clazz = t.getClass();
				Field [] field=clazz.getDeclaredFields();
				for (int i = 0 ; i< field.length; i++) {
					Field declaredField = field[i];
					declaredField.setAccessible( true );
					Object obj = declaredField.get(t);
					if(obj!=null){
					 if(obj instanceof String){
		                	map.put(declaredField.getName(), obj+"");
						}else if(obj instanceof Boolean){
							map.put(declaredField.getName(), Boolean.valueOf(obj.toString()));
						}else if(obj instanceof Integer){
							map.put(declaredField.getName(), Integer.valueOf(obj.toString()));
						}else if(obj instanceof Double){
							map.put(declaredField.getName(), Double.valueOf(obj.toString()));
						}else if(obj instanceof Float){
							map.put(declaredField.getName(), Float.valueOf(obj.toString()));
						}else if(obj instanceof Date){
							map.put(declaredField.getName(), DateUtils.parse(obj.toString()));
						}else if(obj instanceof Timestamp){
							map.put(declaredField.getName(),DateUtils.parse(obj.toString()));
						}else if(obj instanceof BigDecimal){
							map.put(declaredField.getName(), new BigDecimal(Long.valueOf(obj.toString())));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return map;
    }
    
}
