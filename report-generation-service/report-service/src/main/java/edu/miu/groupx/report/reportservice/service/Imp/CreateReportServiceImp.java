package edu.miu.groupx.report.reportservice.service.Imp;

import edu.miu.groupx.report.reportservice.dto.ReportProductDto;
import edu.miu.groupx.report.reportservice.service.CreateReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CreateReportServiceImp implements CreateReportService {
    @Override
    public HttpServletResponse createPdfReportSalesVendor(HttpServletResponse response, List<ReportProductDto> dataList, Map parameters, String pathname) throws IOException, JRException {
        final InputStream stream = new FileInputStream(new File(pathname));
        JasperDesign jasperDesign = JRXmlLoader.load(stream);
        final JasperReport report = JasperCompileManager.compileReport(jasperDesign);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return response;
    }
}
