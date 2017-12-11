package com.blog.service.impl;

import com.blog.service.FileUploadService;
import com.blog.utils.Misc;
import com.blog.utils.PropertyReader;
import com.blog.utils.ResponseUtils;
import com.blog.utils.UploadFile;
import com.blog.vo.ResponseWrapper;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public Map<String,Object> uploadFile(MultipartFile multipartFile, Map<String, Object> map) {
        String showPath = PropertyReader.get("UEditorPtPath", "filePath.properties");
        String filePath =  PropertyReader.get("UEditorPath", "filePath.properties");
        String uuid = UUID.randomUUID().toString();
        String fileName = multipartFile.getOriginalFilename();
        Map<String, Object> m = new HashMap<String, Object>();
        File file = null;
        try {
            file = multipartToFile(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Misc.isStringEmpty(fileName) || file==null){
            m.put("error","图片为空");
        }
        int index=fileName.lastIndexOf(".");
        if(index<1 || !Misc.isImage(file)){
            m.put("error","图片格式错误");
        }
        String suffix = fileName.substring(index);
        if(fileName!= null && file.length() > 2000*1024){
            m.put("error","图片文件过大");
        }

        filePath =filePath+ UploadFile.getYMPath();

        File imageFile = new File(filePath);
        if (!imageFile.isDirectory()) {
            imageFile.mkdirs();
        }
        boolean status = false;
        filePath = filePath+"/"+uuid+suffix;    //上传图片路径
        if (file != null && file.length() > 1000 * 1024) {
            status = UploadFile.copy(file, new File(filePath), suffix.substring(1), 300, 1000 * 1024);
        } else {
            status = UploadFile.copy(file, new File(filePath));
        }
        showPath = showPath+UploadFile.getYMPath()+"/"+uuid+suffix;
        if(status) {
            if(m.size()==0) {
                m.put("state", "SUCCESS");
                m.put("type", ".jpg");//类型
                m.put("size", "99697");//大小
                m.put("name", uuid + suffix);//上传后的文件名
                m.put("url", showPath);//显示路径
                m.put("original", fileName);//原图片名称
            }else{
                return m;
            }
        }
        return m;
    }

    private File multipartToFile(MultipartFile multipartFile) throws IOException {
        CommonsMultipartFile cf = (CommonsMultipartFile)multipartFile;
        //这个myfile是MultipartFile�??
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        //手动创建临时文件
        if(file.length() < 2048){
            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                    file.getName());
            multipartFile.transferTo(tmpFile);
            return tmpFile;
        }
        return file;
    }
}
