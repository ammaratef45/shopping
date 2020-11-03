package edu.miu.groupx.report.reportservice.controller;


import edu.miu.groupx.report.reportservice.dto.ReportProductDto;
import edu.miu.groupx.report.reportservice.feginClient.ProductProxy;
import edu.miu.groupx.report.reportservice.service.CreateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vendors/{vendorId}/reports")
public class ReportController {

    @Autowired
    private CreateReportService createReportService;

    @Autowired
    ProductProxy productProxy;

    @GetMapping
    public void getOrders(HttpServletResponse response, @PathVariable String vendorId) throws URISyntaxException {

        String pathName = ReportController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        pathName = pathName.replaceAll("/target/classes/.*", "/src/main/resources/viewSalesReports.jrxml");
        Map<String, String> parameters = new HashMap();
        List<ReportProductDto> reportProductDtos =  productProxy.salesReportsVendor(Long.parseLong(vendorId));

        System.out.println(reportProductDtos);
        try {

            response = createReportService.createPdfReportSalesVendor(response, reportProductDtos, parameters, pathName);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
    }
}
