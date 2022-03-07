package com.xubo.druid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xubo
 * @Date 2022/3/3 11:39
 * Swagger2 配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<Parameter>();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Druid Rest Api")
                .description("swagger2 接口展示文档")
//                .termsOfServiceUrl("http://192.168.2.96:8085/druid")
                .termsOfServiceUrl("http://127.0.0.1:8089")
                .version("1.0")
                .build();
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("E:\\xubo\\note\\privoce\\privoce.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
