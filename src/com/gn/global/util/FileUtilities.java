/**
 * @author ChaosWong
 * @date 2019/12/27 13:27
 * @title File Utilities Used in Program
 */
package com.gn.global.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtilities {
	public static void writeObj(File file, Object obj) throws IOException {
		boolean isexit = false;
		if (file.exists()) {
			isexit = true;// 序列化文件存在,追加内容
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file,true);
		// 每次new的时候都会写入一个StreamHeader,所以要把屁股后面的StreamHeader去掉
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		long pos = 0;// 可以说是文件的长度
		if (isexit) {
			// getChannel()返回此通道的文件位置，这是一个非负整数，它计算从文件的开始到当前位置之间的字节数
			pos = fileOutputStream.getChannel().position() - 4;// StreamHeader有4个字节所以减去
			// 将此通道的文件截取为给定大小
			fileOutputStream.getChannel().truncate(pos);
		}

		objectOutputStream.writeObject(obj);
		// 关闭流
		objectOutputStream.close();
		fileOutputStream.close();
	}
	public static List<Object> readObj( File file ) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		List<Object> list = new ArrayList<Object>();
		try {
			fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			while (fileInputStream.available() > 0) {
				list.add((Object) objectInputStream.readObject());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

}

