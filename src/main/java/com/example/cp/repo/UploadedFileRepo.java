package com.example.cp.repo;

import com.example.cp.domain.UploadedFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepo extends JpaRepository<UploadedFiles,Long> {
    UploadedFiles findUploadedFilesByFileName(String fileName);

}
