package com.manager.web.controller;

import com.manager.service.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictureController {

    @Autowired
    private PictureServiceImpl pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map pictureUpload(MultipartFile uploadFile) {
        Map map = pictureService.uploadFile(uploadFile);
        return map;
    }

}
