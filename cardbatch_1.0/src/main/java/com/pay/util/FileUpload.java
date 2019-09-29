package com.pay.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.struts.upload.FormFile;

public class FileUpload {
	public static String uploadFile(String path, FormFile file) {
		
		String fileName = getExtention(file.getFileName());

		InputStream streamin = null;
		OutputStream bos = null;
		try {
			File filepath = new File(path);
			if (!filepath.exists()) {
				filepath.mkdirs();
			}
			fileName = UUID.randomUUID() + fileName;
			streamin = file.getInputStream();
			bos = new FileOutputStream(path + "/" + fileName);
			int bytesRead = 0;
			byte[] sum = new byte[8192];
			int i = 0;
			while ((bytesRead = streamin.read(sum, 0, 8192)) != -1) {
				i = i + bytesRead;
				bos.write(sum, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (streamin != null) {
					streamin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "/merchantPic/" + fileName;
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		if (pos < 1) {
			return "";
		}
		return fileName.substring(pos, fileName.length());
	}
}
