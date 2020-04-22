package com.etoak.controller;

import com.etoak.bean.House;
import com.etoak.service.HouseService;
import com.etoak.util.ValidationUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
    public String add(@RequestParam("file") MultipartFile file,  House house) throws IOException,IllegalStateException {
        //对参数进行校验
       /* List<ObjectError> allErrors = bindingResult.getAllErrors();
        if(CollectionUtils.isNotEmpty(allErrors)){
            StringBuffer msgBuffer =new StringBuffer();
            for (ObjectError objectError:allErrors){
                String message = objectError.getDefaultMessage();
                msgBuffer.append(message).append(";");
            }
            throw new RuntimeException("参数校验失败："+msgBuffer.toString());
        }*/
        ValidationUtil.validate(house);
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
