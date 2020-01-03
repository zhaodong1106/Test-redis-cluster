package com.example.demo.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yef
 * @date 2019/8/7
 */
@Component
public class UploadUtils {
    private  static final DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static  final String TARGET_DIR="E:\\新建文件夹";
    private static  final String DOWNLOAD_DIR="E:\\新建文件夹2";
    public   String upload(MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        String dateTimePattern=LocalDate.now().format(dtf);
        String targetFileName=TARGET_DIR+File.separator+dateTimePattern+File.separator+filename;
        try {
            File file = new File(targetFileName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFileName;
    }

    public ResponseEntity<byte[]> download(HttpServletResponse response) throws IOException {
        File file=new File("E:\\新建文件夹\\2019-08-07\\ajly.xlsx");
        HttpHeaders headers=new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "ajly.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
        return responseEntity;
    }


}
