package com.sateraito.mitap.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sateraito.mitap.model.response.ReponseMdl;



@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	
	@RequestMapping(value = "/test", method = { RequestMethod.POST}) 
	public ResponseEntity<ReponseMdl> test(Model model, HttpServletRequest request) {
		Path fileStorageLocation = Paths.get("upload" + "").toAbsolutePath().normalize();
		try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
		ReponseMdl reponseMdl = new ReponseMdl(0,  fileStorageLocation.toString());
		return new ResponseEntity<>(reponseMdl, HttpStatus.OK);
	}


	/**
	 * Xử dụng để upload 1 hình ảnh từ client nằm ngoài project này (angular 5)
	 */
	@RequestMapping(value = "/image", method = { RequestMethod.POST}) 
	public ResponseEntity<ReponseMdl> handleImageUpload(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
		String WORKSPACE = getPath();
		try {
			String originalFileName = file.getOriginalFilename();
			String suffix = "." + FilenameUtils.getExtension(originalFileName);
			File destFile = File.createTempFile(originalFileName.replaceAll(suffix, ""), suffix, new File(WORKSPACE  + "upload"));
			FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
			String uri = request.getScheme() + "://" +   // "http" + "://
		             request.getServerName() +       // "myhost"
		             ":" + request.getServerPort(); // ":" + "8080"
			String url = uri + "/upload/" + destFile.getName();
			ReponseMdl reponseSuccess = ReponseMdl.getInsSuccess();
			reponseSuccess.setData(url);
			return new ResponseEntity<>(reponseSuccess, HttpStatus.OK);
		} catch (Exception e) {
			ReponseMdl reponseError = ReponseMdl.getInsErrorDefault();
			reponseError.setMessage(e == null ? "" : e.toString() + " : " + WORKSPACE);
			return new ResponseEntity<>(reponseError, HttpStatus.OK);
		}
	}
	

	public String getPath() {
		try {
			String path = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = URLDecoder.decode(path, "UTF-8");
			String pathArr[] = fullPath.split("/target/classes/");
			fullPath = pathArr[0];
			fullPath = new File(fullPath).getPath();
			return fullPath + "\\src\\main\\webapp\\";
		} catch (Exception e) {
			return "";
		}
	}
	
}
