package com.jm.oss.controller;

import com.jm.commonutils.R;
import com.jm.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Api(description = "阿里云oss服务")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("上传文件")
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
