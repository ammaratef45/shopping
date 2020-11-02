package edu.miu.groupx.report.reportservice.service;

import edu.miu.groupx.report.reportservice.dto.ReportProductDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface CreateReportService {
    public HttpServletResponse createPdfReportSalesVendor(HttpServletResponse response, List<ReportProductDto> dataList, Map parameters, String pathname) throws IOException, JRException;
}
