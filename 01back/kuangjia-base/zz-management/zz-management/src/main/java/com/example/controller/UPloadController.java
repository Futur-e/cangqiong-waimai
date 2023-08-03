package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UPloadController {
  /*  @PostMapping("upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("上传的文件{},{},{}",username,age,image );
//        获取原始文件名
        String  originalFilename = image.getOriginalFilename();

//        使用uuid构造文件唯一标识
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        log.info(newFileName);
        image.transferTo(new File("D:\\project\\zwenjian\\" + newFileName));
        return Result.success();
    }*/
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传");
        //调用阿里云oss文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传成功，文件访问的url：{}",url);
        return Result.success(url);
    }
}
