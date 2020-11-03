package edu.miu.groupx.product.productservice.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


public class S3Utils {
    static final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    static final String bucket_name = "shopping-pm";
    static List<String> listObjects() {
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

    static void downloadObject(String key_name) throws IOException {
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
