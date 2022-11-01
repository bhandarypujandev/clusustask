package com.example.cp.controller;

import com.example.cp.service.UploadService;
import com.example.cp.service.impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/upload")
public class UploadController {

    private UploadServiceImpl uploadService;

    @Autowired
    public UploadController(UploadServiceImpl uploadService) {
        this.uploadService =uploadService;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam MultipartFile fxDealFile){
        uploadService.uploadFile(fxDealFile);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
