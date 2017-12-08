package com.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileUploadService {

    Map<String,Object> uploadFile(MultipartFile multipartFile, Map<String, Object> map);

}
