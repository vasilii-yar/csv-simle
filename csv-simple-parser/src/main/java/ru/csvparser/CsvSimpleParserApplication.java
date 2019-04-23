package ru.csvparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CsvSimpleParserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CsvSimpleParserApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CsvSimpleParserApplication.class);
    }

}
