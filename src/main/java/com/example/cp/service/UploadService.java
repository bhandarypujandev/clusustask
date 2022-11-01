package com.example.cp.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void uploadFile(MultipartFile fxDeal);
}
