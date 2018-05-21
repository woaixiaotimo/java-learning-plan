package com.manager.service.impl;

import com.common.utils.FtpFileTransfer;
import com.common.utils.FtpUtil;
import com.common.utils.IDUtils;
import com.manager.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;

    @Value("${FTP_PORT}")
    private Integer FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Override
    public Map uploadFile(MultipartFile multipartFile) throws IOException {

        //取原始文件名
        String oldName = multipartFile.getOriginalFilename();

        //生成新文件名
        //UUID.randomUUID()
        String newName = IDUtils.genImageName();

        newName += oldName.substring(oldName.lastIndexOf("."));
        //图片上传
        FtpFileTransfer ftp = new FtpFileTransfer(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);

        boolean result = ftp.uploadFile(multipartFile.getInputStream(), FTP_BASE_PATH, newName);

        Map resultMap = new HashMap();
        if (!result) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传失败");
        } else {
            resultMap.put("error", 0);
            resultMap.put("url", "ftp://"+FTP_ADDRESS);
            resultMap.put("message", "文件上传成功");
        }

    }
}
