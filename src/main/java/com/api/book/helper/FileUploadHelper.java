package com.api.book.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

//	public final String UPLOAD_DIR = "E:\\Java Project\\bootrest\\src\\main\\resources\\static\\image"; // to serve static  folder
	public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath(); // dynamic path file will save in target folder
	
	FileUploadHelper()throws IOException {
		// TODO Auto-generated constructor stub
	}
	
	public boolean uploadFile(MultipartFile multipart)
	{
		boolean f=false;
		try {
			
//			Example using stream API comming from java.io package we can use nio package instead io package
//			InputStream is = multipart.getInputStream();
//			byte data[] = new byte[is.available()];
//			is.read(data);
//			
//			// write in file
//			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+multipart.getOriginalFilename());
////			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+multipart.getOriginalFilename());
//			
//			fos.write(data);
//			fos.flush();
//			fos.close();
//			f=true;
			
//			using nio package ****************
			Files.copy(multipart.getInputStream(),Paths.get(UPLOAD_DIR + File.separator + multipart.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING );
			f=true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
