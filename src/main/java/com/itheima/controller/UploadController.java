package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/11 19:24
 */
@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传：{}{}{}", name, age, image);

        //1.获取原始文件名
        String originalFilename = image.getOriginalFilename();
        //2.构造不重复的文件名--使用uuid(通用唯一识别码)
        int index = originalFilename.lastIndexOf(".");//拿到最后一个"."的index
        String substring = originalFilename.substring(index);//截取子串
        String newFileName = UUID.randomUUID().toString() + substring;//拼接成新文件名
        log.info("新文件名为:{}", newFileName);

        //3.将文件存储在本机的磁盘目录中
        image.transferTo(new File("/Users/meng/Downloads/Icon/" + newFileName));

        return Result.success();
    }
}
