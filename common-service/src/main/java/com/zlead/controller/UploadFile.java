package com.zlead.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zlead.common.FileUpload;
import com.zlead.domain.ApiResult;
import com.zlead.domain.ServiceReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/common")
public class UploadFile {

    Logger logger= LoggerFactory.getLogger(UploadFile.class);
    @PostMapping("/upload/{shopId}/{type}")
    @HystrixCommand(fallbackMethod = "uploadFallback")
    public ApiResult uploadFile(@RequestParam("file") MultipartFile[] files,
                                @PathVariable("shopId") String shopId,
                                @PathVariable("type") String type) {
        List<String> list=new ArrayList<>();
        try {
            logger.info("上传图片开始");
            if (files != null) {
                Arrays.asList(files).forEach(e -> {
                    String uploadPath = "D:/".concat(shopId).concat("/").concat(type);
                    String filename = FileUpload.getFileName(e.getOriginalFilename());
                    if (FileUpload.isErrorSuffixFile(filename)) {
                        throw new RuntimeException(ServiceReturnCode.IMAGE_FORMAT_ERROR.getMessage());
                    }
                    File ftemp = new File(uploadPath);
                    if (!ftemp.exists()) ftemp.mkdirs();
                    String finalPath = uploadPath.concat("/").concat(filename);
                    try {
                        e.transferTo(new File(finalPath));//存放在本地
                        list.add(finalPath);
                    } catch (IOException e1) {
                        throw new RuntimeException(ServiceReturnCode.IMAGE_UPLOAD_ERROR.getMessage());
                    }
                });
            } else {
                return ApiResult.isErrMessage(ServiceReturnCode.NO_UPLOAD_IMAGE);
            }
        }catch (Exception e){
            logger.info("上传图片有异常".concat(e.getMessage()));
            return ApiResult.isErrMessage(e.getMessage());
        }
        return ApiResult.isOkNoToken(ServiceReturnCode.IMAGE_UPLOAD_SUCCESS,list);
    }

    public String uploadFallback(@RequestParam("files") MultipartFile[] files,
                                 @PathVariable("shopId") String shopId,
                                 @PathVariable("type") String type) {
        System.out.println("上传失败");
        return "文件上传失败";
    }
}