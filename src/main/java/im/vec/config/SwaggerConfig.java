package im.vec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2018-12-04 19:36:05
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket ProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .build()
                .apiInfo(productApiInfo());
    }

    private ApiInfo productApiInfo() {
//        String title, String description, String version, String termsOfServiceUrl, Contact contact, String license, String licenseUrl, Collection< VendorExtension > vendorExtensions
        return new ApiInfo(
                "xxx",
                "xxx",
                "0.0.1",
                "",
                DEFAULT_CONTACT,
                "",
                "",
                new ArrayList<>());
    }

}
