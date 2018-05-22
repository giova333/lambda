package com.gladunalexander.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.gladunalexander.aws.AWSFactory;
import java.io.File;

/**
 * Created by Alexander Gladun on 22/05/2018.
 */
public class S3Service {

    private static final String BUCKET_NAME = "anabol-polly-mp3";
    private static final String BUCKET_DOMAIN = "https://s3.amazonaws.com/";
    private static final String FILE_FORMAT = ".mp3";

    private final AmazonS3Client client;

    public S3Service() {
        client = AWSFactory.getS3();
    }

    public String uploadToBucket(File file) {
        String fullFileName = file.getName() + FILE_FORMAT;
        client.putObject(BUCKET_NAME, fullFileName, file);
        return BUCKET_DOMAIN + BUCKET_NAME + "/" + fullFileName;
    }
}
