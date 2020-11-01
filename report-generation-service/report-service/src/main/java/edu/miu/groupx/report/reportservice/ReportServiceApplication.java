package edu.miu.groupx.report.reportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan({"edu.miu.groupx.report.reportservice", "edu.miu.groupx.report.reportservice.feginClient"})
@EnableFeignClients(basePackages = {"edu.miu.groupx.report.reportservice", "edu.miu.groupx.report.reportservice"})
@SpringBootApplication
@EnableEurekaClient
public class ReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportServiceApplication.class, args);
	}

}
