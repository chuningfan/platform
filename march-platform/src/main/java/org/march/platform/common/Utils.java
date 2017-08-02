package org.march.platform.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Utils {
	
	private static final Logger LOG = Logger.getLogger(Utils.class);
	
	public static <T> Collection<T> ifNullReturnEmpty(Collection<T> coll) {
		return coll == null ? Collections.emptyList() : coll;
	}
	
	public static boolean isEmpty(Object[] objs) {
		return objs == null || objs.length == 0;
	}
	
	public static void writeFile(InputStream in, String dir, String fileName) throws FileNotFoundException, IOException {
		dir = dir.replace("\\", "/");
		File directory = new File(dir + "/");
		if (!directory.exists()) {
			directory.mkdirs();
		}
		String path = dir + "/" + fileName;
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		try (OutputStream out = new FileOutputStream(file)) { 
				int read = 0;  
            	byte[] bytes = new byte[1024];
	            while ((read = in.read(bytes)) != -1) {  
	                out.write(bytes, 0, read);  
	            }
         };
	}
	
	public static <O, N> List<N> convertListToTarget(List<O> originalList, List<N> newList, Class<N> clazz) throws InstantiationException, IllegalAccessException {
		for (O originalObject: ifNullReturnEmpty(originalList)) {
			N newObject = clazz.newInstance();
			newObject = convertObjectToTarget(originalObject, newObject);
			newList.add(newObject);
		}
		return newList;
	}
	
	public static <O, N> N convertObjectToTarget(O originalObject, N newObject) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz_o = originalObject.getClass();
		List<Field> fields_o = getFields(clazz_o, new ArrayList<Field>());
		Class<?> clazz_n = newObject.getClass();
		List<Field> fields_n = getFields(clazz_n, new ArrayList<Field>());
		for (Field field_o: ifNullReturnEmpty(fields_o)) {
			for (Field field_n: ifNullReturnEmpty(fields_n)) {
				if (field_o.getName().equals(field_n.getName())) {
					field_n.setAccessible(true);
					field_o.setAccessible(true);
					field_n.set(newObject, field_o.get(originalObject));
				}
			}
		}
		return newObject;
	}
	
	public static List<Field> getFields(Class<?> clazz, List<Field> emptyList) {
		Class<?> superClass = clazz.getSuperclass();
		emptyList.addAll(Arrays.asList(clazz.getDeclaredFields()));
		if (superClass.getName().equals("java.lang.Object")) {
			return emptyList;
		} else {
			return getFields(superClass, emptyList);
		}
	}
	
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}
	
	public static Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
		try {
			return clazz.getDeclaredField(name);
		} catch (NoSuchFieldException | SecurityException e) {
			Class<?> superClass = clazz.getSuperclass();
			if (!superClass.getName().equals("java.lang.Object")) {
				return getField(superClass, name);
			} else {
				throw new NoSuchFieldException("There is not a field named " + name);
			}
		}
		
	}
}	
