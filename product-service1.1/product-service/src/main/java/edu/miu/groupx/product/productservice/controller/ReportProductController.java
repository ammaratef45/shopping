package edu.miu.groupx.product.productservice.controller;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.util.List;
import org.modelmapper.ModelMapper;


@RestController
@RequestMapping("/report")
public class ReportProductController {

    @Autowired
    ReportService reportService;

    @Autowired
    private ModelMapper modelMapper;
/*
    @GetMapping("/salesForVendor/{vendorId}")
    public List<ReportProductDto> salesReportsVendor(@PathVariable long vendorId) {
        List<Product> products = reportService.getReportProduct(vendorId);

        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReportProductDto convertToDto(Product product) {
        ReportProductDto reportProductDto = modelMapper.map(product, ReportProductDto.class);
        return reportProductDto;
    }
    */

}
