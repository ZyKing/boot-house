package com.etoak.controller;

import com.etoak.bean.House;
import com.etoak.service.HouseService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/house")
public class HouseController {
    @Value("${upload.dir}")
    private String uploadDirectory;
    @Value("${upload.savePathPrefix}")
    private String savePathPrefix;
    @Autowired
    HouseService houseService;
    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "house/add";
    }
    @PostMapping("/add")
    public String add(@RequestParam("file") MultipartFile file, House house) throws IOException,IllegalStateException {
         //上传文件
        String originalFilename = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(originalFilename);
        String prefix = UUID.randomUUID().toString().replaceAll("-","");
        String newFileName = prefix+"_"+originalFilename;

        File destFile = new File(this.uploadDirectory,newFileName);
        file.transferTo(destFile);
        house.setPic(this.savePathPrefix+newFileName);
        houseService.addHouse(house);
        return "redirect:/house/toAdd";
    }
}
