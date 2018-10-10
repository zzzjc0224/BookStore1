package org.lq.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.lq.bean.*;
import org.lq.exception.NoSuchGenderException;
public class FormUtil {

	public static void populate(Map<String, String[]> map, FormBean rfb) throws IllegalArgumentException, IllegalAccessException {
		Field [] fields=rfb.getClass().getDeclaredFields();
		for(Field field:fields) {
			String name=field.getName();
			String [] value=map.get(name);
			if(value==null)continue;
			field.setAccessible(true);
			field.set(rfb, value[0]);
		}
		
	}

	public static Map<String, String> validate(FormBean rfb) {
		Map<String,String> errors = null;
		
		if( rfb.getUsername()==null ||rfb.getUsername().matches("^[a-zA-Z0-9_]{6,24}$") ==false ) {
			if(errors==null) {
				errors = new HashMap<>();
			}
			errors.put("username", "用户名格式错误");
		}
		if( rfb.getPassword()==null || rfb.getPassword().matches("^\\w{6,24}$") == false) {
			if(errors==null) {
				errors = new HashMap<>();
			}
			errors.put("password", "密码格式错误");
		}
		if( rfb.getEmail()==null || rfb.getEmail().matches("^[a-zA-Z0-9_\\-]{1,24}@(\\w+.)+(org|com|cn)$") == false ) {
			if(errors==null) {
				errors = new HashMap<>();
			}
			errors.put("email", "邮箱格式错误");
		}
		if( rfb.getTelephone()==null || rfb.getTelephone().matches("^1\\d{10}$") == false) {
			if(errors==null) {
				errors = new HashMap<>();
			}
			errors.put("telephone", "电话格式错误");
		}
		return errors;
	}

	public static void populate(FormBean rfb, User user) throws NoSuchGenderException {
		user.setUsername(rfb.getUsername());
		user.setPassword(rfb.getPassword());
		user.setEmail(rfb.getEmail());
		user.setGender(rfb.getGender());
		user.setIntroduce(rfb.getIntroduce());
		user.setTelephone(rfb.getTelephone());
		
	}

}
