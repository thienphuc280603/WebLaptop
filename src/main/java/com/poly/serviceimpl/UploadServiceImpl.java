package com.poly.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl {
	@Autowired
	ServletContext app;
	
	public String save(MultipartFile file, String folder) throws IOException {
	    String projectDir = System.getProperty("user.dir");
	    String fullPath = projectDir + "/src/main/resources/static/" + folder;
		File dir = new File(fullPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
        String fileName = file.getOriginalFilename();
		try {
            File uploadedFile = new File(dir, fileName);
            
			file.transferTo(uploadedFile);
			System.out.println(uploadedFile.getAbsolutePath());
			return "Thanh cong";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
