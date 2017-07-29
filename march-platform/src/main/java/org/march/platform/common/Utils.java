package org.march.platform.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;

public class Utils {
	
	public static <T> Collection<T> ifNullReturnEmpty(Collection<T> coll) {
		return coll == null ? Collections.emptyList() : coll;
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
	
}	
