package com.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FtpTest {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\admin\\Desktop\\常用文件/A.png";
        String ftpIp = "192.168.42.137";
        int ftpPort = 21;
        String ftpUsername = "what";
        String ftpPwd = "meiyoumima";
        String ftpWritePath = "/write";
        String writePath = "";
        String uploadFileName = "A.png";

        FtpFileTransfer ftp =new FtpFileTransfer(ftpIp,ftpPort,ftpUsername,ftpPwd);

        boolean result = false;
        result = ftp.uploadFile(new File(filePath),ftpWritePath,uploadFileName);
        System.out.println("result = " + result);
    }
}
