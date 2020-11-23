package com.jm.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.jm.oss.service.OssService;
import com.jm.oss.utils.ConstantPropertiesUtils;
import com.jm.oss.utils.setUploadFolder;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 图片上传oss实现类
 * @author marc
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POIND;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
            String datePath = new DateTime().toString("yyyy/MM/dd");
            String saveName = setUploadFolder.avatarLocal + "/" + datePath + "/" + fileName;
            ossClient.putObject(bucketName, saveName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return "https://" + bucketName + "." + endpoint + "/" + saveName;

        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }
}
