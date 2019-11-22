package com.sateraito.mitap.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.service.UploadImageService;



@Controller
@RequestMapping(value = "/upload")
public class UploadController extends MitapController{
	
	@RequestMapping(value = "/local_upload_image", method = { RequestMethod.POST}) 
	public ResponseEntity<ReponseMdl> localUploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		return uploadImageService.localUploadImage(file, request);
	}
	
	@GetMapping(UploadImageService.GET_URL_STREAM_IMAGE + "/{fileName:.+}")
	public ResponseEntity<Resource> localUploadImage(@PathVariable String fileName, HttpServletRequest request) {
		return uploadImageService.getUrlStreamImage(fileName, request);
	}
	
	@RequestMapping(value = "/upload_image_get_url", method = { RequestMethod.POST})
	public ResponseEntity<ReponseMdl> uploadImageGetUrl(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		return uploadImageService.uploadImageGetUrl(file, request);
	}
	
	
	@RequestMapping(value = "/test", method = { RequestMethod.POST}) 
	public ResponseEntity<Resource> test(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {
		Path fileStorageLocation = Paths.get("upload").toAbsolutePath().normalize();
		try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = fileStorageLocation.resolve(fileName);
        try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
		}
        
//		ReponseMdl reponseMdl = new ReponseMdl(0,  fileStorageLocation.toString() + " : " + targetLocation + " : fileName: " + fileName);
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        try {
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				String contentType = uploadImageService.getContentTypeFile(resource, request);
				return ResponseEntity.ok()
		                .contentType(MediaType.parseMediaType(contentType))
		                .body(resource);
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
		} catch (MalformedURLException e) {
		}
        return null;
	}
	
	
	
}
