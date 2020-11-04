package edu.miu.groupx.product.productservice.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import edu.miu.groupx.product.productservice.models.dtos.ImageResponse;
import edu.miu.groupx.product.productservice.service.ProductService;
import edu.miu.groupx.product.productservice.utils.S3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.ProductImages;
import edu.miu.groupx.product.productservice.service.CategoryService;
import edu.miu.groupx.product.productservice.service.ProductImagesService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class ProductImagesController {

    @Autowired
    ProductImagesService productImagesService;

    @Autowired
    ProductService productService;

    @GetMapping("/products/res/{id}")
    ImageResponse getProductImages(@PathVariable long id) {
        return this.productImagesService.getProductImages(id);
    }

    @PostMapping("/products/res")
    public String uploadImage(@RequestParam("productId") long productId, @RequestParam("vendorId") long vendorId, @RequestParam("file") MultipartFile file) {
        boolean ownership = this.productService.checkProductOwnership(productId, vendorId);
        if (!ownership)
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "ownership check failed"
            );
        String url = S3Utils.uploadFile(file, productId, vendorId);
        this.productImagesService.saveProductImage(productId, url);
        return url;

    }

    @DeleteMapping("/products/res")
    public String deleteImage(@RequestParam("url") String url, @RequestParam("productId") long productId, @RequestParam("vendorId") long vendorId) {
        boolean ownership = this.productService.checkProductOwnership(productId, vendorId);
        if (!ownership)
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "ownership check failed"
            );
        String response = S3Utils.deleteFileFromS3Bucket(url);
        this.productImagesService.deleteProductImage(productId, vendorId, url);
        return response;
    }


}
