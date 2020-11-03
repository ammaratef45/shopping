package edu.miu.groupx.report.reportservice.feginClient;

import edu.miu.groupx.report.reportservice.dto.ReportProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "product-service",url = "http://localhost:8080")
public interface ProductProxy {

    @GetMapping("/report/salesForVendor/{vendorId}")
    List<ReportProductDto> salesReportsVendor(@PathVariable long vendorId);

}
