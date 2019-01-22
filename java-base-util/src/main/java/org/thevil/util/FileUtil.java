package org.thevil.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 搜索整个文件目录中包含的内容，并替换为目标内容
 * @author Kevin zhaosheji
 * @date 2019年1月22日
 */
public class FileUtil {

	public static void main(String args[]) throws Exception {
		String demoURL=args[0]; //文件路径
		String content = args[1]; //文件内容
		String target = args[2];//要替换的内容
		FileUtil.iteratorFile(new File(demoURL),content,target);
	}
	
	public static void iteratorFile(File file,String content,String target) throws IOException {
		if(file.exists()) {
			if(file.isDirectory() && !file.isFile()) {
				File[] list = file.listFiles();
				for(int i = 0; i<list.length ; i++) {
					File filename = list[i];
					if(file.isDirectory() && !file.isFile()) {
						iteratorFile(filename,content,target);
					}else {
						createAndupdateContent(filename,content,target);
					}
				}
			}else {
				createAndupdateContent(file,content,target);
			}
		}else {
			throw new IOException("文件没有找到");
		}
	}
	
	public static void createAndupdateContent(File oldFile ,String content,String target) throws IOException {
		String newPath = oldFile.getPath().replace(content, target);
		String dirNewFile = newPath.replace(oldFile.getName(),"");
		File newFile =new File(newPath);
		File newFileDir = new File(dirNewFile);
		if(!newFileDir.exists()) {
			newFileDir.mkdirs();
		}
		if(!newFile.exists()) {
			newFileDir.createNewFile();
		}
		if(!oldFile.isDirectory() && oldFile.isFile()) {
			BufferedReader bufReader = new BufferedReader(new FileReader(oldFile));
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter(newFile));
			String input =null;
			while((input = bufReader.readLine())!=null) {
				//替换内容
				if(input.contains(content)) {
					input = input.replace(content, target);
				}
				bufWriter.write(input+"\r\n");
			}
	        bufWriter.close();
	        bufReader.close();
		}
	}
}
