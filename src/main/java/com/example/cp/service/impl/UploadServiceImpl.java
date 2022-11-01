package com.example.cp.service.impl;

import com.example.cp.domain.UploadedFiles;
import com.example.cp.exception.EmptyFileException;
import com.example.cp.exception.InvalidFileException;
import com.example.cp.exception.OtherExceptions;
import com.example.cp.repo.UploadedFileRepo;
import com.example.cp.service.FxDealService;
import com.example.cp.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    private UploadedFileRepo uploadedFileRepo;
    private FxDealServiceImpl fxDealService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);


    @Autowired
    public UploadServiceImpl(UploadedFileRepo uploadedFileRepo, FxDealServiceImpl fxDealService) {
        this.uploadedFileRepo = uploadedFileRepo;
        this.fxDealService=fxDealService;
    }

    @Override
    public void uploadFile(MultipartFile fxDeal) {
        if(checkIfFileIsValid(fxDeal.getOriginalFilename())){
            try{
                if(fxDeal.isEmpty()) throw new EmptyFileException();
                UploadedFiles uploadedFile = uploadedFileRepo.findUploadedFilesByFileName(fxDeal.getOriginalFilename());
                if(uploadedFile !=null){
                    throw new OtherExceptions("File Already Uploaded");
                }
                LOGGER.info("Reading file");
                List<String> rows = new ArrayList<>();
                InputStream inputStream = fxDeal.getInputStream();
                String row;
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                int lineCount =0 ;
                int headers = 0;
                while((row=br.readLine()) != null){
                    rows.add(row);
                    if(lineCount == 0)
                        headers = row.split(",").length;
                    else{
                        if(row.split(",").length != headers)
                            LOGGER.warn("Invalid Row "+row);
                        //Save row even if invalid as on requirement
                        fxDealService.saveFxDeal(row);
                    }
                    lineCount++;
                }
                if(lineCount > 0)uploadedFileRepo.save(new UploadedFiles(fxDeal.getOriginalFilename()));
            }catch(Exception e){
                    throw new OtherExceptions(e.getMessage());
            }
        }
        else{
                throw new InvalidFileException();
        }
    }

    //File is valid if it is csv file
    private boolean checkIfFileIsValid(String fileName){
        return fileName.endsWith(".csv");
    }
}
