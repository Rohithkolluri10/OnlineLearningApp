package com.onlineLearningPlatform.Service.Impl;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.onlineLearningPlatform.config.Awsclient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
public class S3fileuploader {


    private final Awsclient awsclient;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketname;

    public S3fileuploader(Awsclient awsclient) {
        this.awsclient = awsclient;
    }

    public String uploadfile(MultipartFile multipartFile) throws IOException {
        File file = convertMultiparttoFile(multipartFile);
        String filename = generatefilename(multipartFile);
        log.info("uploading the file to the bucket", bucketname);
        awsclient.amazonS3client().putObject(new PutObjectRequest(bucketname,filename,file));
        String s3Url = awsclient.amazonS3client().getUrl(bucketname,filename).toString();

        file.delete();
        return s3Url;

    }

    private File convertMultiparttoFile(MultipartFile multipartFile) throws IOException {
        File convfile = new File(multipartFile.getName());
        FileOutputStream fos = new FileOutputStream(convfile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convfile;

    }

    private String generatefilename(MultipartFile multipartFile){
        return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace("","_");
                 }
}
