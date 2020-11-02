package edu.miu.groupx.product.productservice.service;


import edu.miu.groupx.product.productservice.models.Product;

import java.util.List;

public interface ReportService {

    public List<Product> getReportProduct(Long id);
}
