package edu.miu.groupx.product.productservice.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


public class S3Utils {
    static AWSCredentials credentials = new BasicAWSCredentials(
            "AKIAUX7GRXL44L7Q5PXT",
            "SEQpzm9mYpFnyerbrEwfa2ysFGVx05HPIubHoga7"
    );
    public static final AmazonS3 s3 = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_1)
            .build();

    static final String bucket_name = "shopping-pm";

    static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    static void uploadFileTos3bucket(String fileName, File file) {
        s3.putObject(new PutObjectRequest(bucket_name, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public static List<String> listObjects() {
        List<String> res = new ArrayList<>();
        ListObjectsV2Result result = s3.listObjectsV2(bucket_name);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            res.add(os.getKey());
        }
        return res;
    }

    public static void uploadObject(String key_name, String file_path) {
        s3.putObject(bucket_name, key_name, new File(file_path));
    }

    public static String uploadFile(MultipartFile multipartFile, long productId, long vendorId) {

        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile, productId, vendorId);
            fileUrl = bucket_name + ".s3.amazonaws.com/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    static String generateFileName(MultipartFile multiPart, long productId, long vendorId) {
        return vendorId + "-" + productId + "-" + new Date().getTime();
    }

    public static String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        System.out.println(fileName);
        try {
            s3.deleteObject(new DeleteObjectRequest(bucket_name, fileName));
        } catch (Exception e) {
            System.out.println(e);
        }
        ;
        return "Successfully deleted";
    }

    public static void downloadObject(String key_name) throws IOException {
        S3Object o = s3.getObject(bucket_name, key_name);
        S3ObjectInputStream s3is = o.getObjectContent();
        FileOutputStream fos = new FileOutputStream(new File(key_name));
        byte[] read_buf = new byte[1024];
        int read_len;
        while ((read_len = s3is.read(read_buf)) > 0) {
            fos.write(read_buf, 0, read_len);
        }
        s3is.close();
        fos.close();
    }

    /*
     * public static AmazonS3 getS3() { return s3; }
     */

    public static String getBucketName() {
        return bucket_name;
    }


}
