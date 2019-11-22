package com.sateraito.mitap.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.utils.Log;

@Service
@Transactional
public class UploadImageService extends MitapService {
	public static final String GET_URL_STREAM_IMAGE = "/get_url_stream_image/";
	private Path fileStorageLocation;
	private static String folderUpload = "upload";
	private String notStoreFile = "Could not store file %s. Please try again!";
	private String fileNotFound = "File not found %s";
	
	public ResponseEntity<ReponseMdl> localUploadImage(MultipartFile file, HttpServletRequest request) {
		fileStorageLocation = Paths.get(folderUpload).toAbsolutePath().normalize();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String name = FilenameUtils.getBaseName(fileName);
		String fileExtension = FilenameUtils.getExtension(fileName);
		String newName = name + "_" + String.valueOf(new Date().getTime()) + "." + fileExtension;
		if (!isFileAllow(fileExtension)) {
            throw new RuntimeException(String.format(notStoreFile, fileName));
        }
		try {
			Files.createDirectories(fileStorageLocation);
	        Path targetLocation = fileStorageLocation.resolve(newName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return responseSuccessDefault(newName);
		} catch (IOException e) {
			throw new RuntimeException(String.format(notStoreFile, fileName));
		}
	}

	public ResponseEntity<Resource> getUrlStreamImage(String fileName, HttpServletRequest request) {
		try {
			Path filePath = fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				String contentType = getContentTypeFile(resource, request);
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
	        } else {
	            throw new RuntimeException(String.format(fileNotFound, fileName));
	        }
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<ReponseMdl> uploadImageGetUrl(MultipartFile file, HttpServletRequest request) {
		ReponseMdl responeUpload = localUploadImage(file, request).getBody();
		if(responeUpload != null && responeUpload.getErrorCode() == ReponseMdl.SUCCESS) {
			StringBuilder sb = new StringBuilder().append(request.getScheme()).append("://")
					.append(request.getServerName()).append(":").append(request.getServerPort())
					.append("/").append(folderUpload).append(GET_URL_STREAM_IMAGE).append(responeUpload.getData().toString());
			return responseSuccessDefault(sb.toString());
		} else {
			return responseError(UPLOAD_IMAGE_FAIL);
		}
	}
	
	public String getContentTypeFile(Resource resource, HttpServletRequest request) {
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            Log.d("Could not determine file type.");
        }
        return contentType;
    }
	
	private boolean isFileAllow(String fileExtension){
        return fileExtension.toUpperCase().equals("PNG")
		        || fileExtension.toUpperCase().equals("JPG")
		        || fileExtension.toUpperCase().equals("JPEG")
		        || fileExtension.toUpperCase().equals("XLSX")
		        || fileExtension.toUpperCase().equals("XLS")
		        || fileExtension.toUpperCase().equals("DOCS")
		        || fileExtension.toUpperCase().equals("DOC")
		        || fileExtension.toUpperCase().equals("PDF");
    }

}
