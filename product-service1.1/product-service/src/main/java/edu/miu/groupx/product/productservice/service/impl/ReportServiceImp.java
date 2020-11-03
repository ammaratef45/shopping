package edu.miu.groupx.product.productservice.service.impl;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ReportServiceImp implements ReportService {

    @Autowired
    ProductRepository productRepository;

    @Override

    public List<Product> getReportProduct(Long id) {

        return null;
    }
}
