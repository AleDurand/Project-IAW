package proyect.swagger;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration bean to set up Swagger.
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@PropertySource("classpath:swagger.properties")
public class SwaggerConfiguration {
	
	
    @Value("${swagger.resourcePackage}")
    private String resourcePackage;

    @Value("${swagger.basePath}")
    private String basePath;

    @Value("${swagger.apiVersion}")
    private String apiVersion;
    
    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;
    
    @Value("${swagger.terms}")
    private String terms;
    
    @Value("${swagger.contact}")
    private String contact;
    
    @Value("${swagger.licence}")
    private String licence;
    
    @Value("${swagger.licence_url}")
    private String licenceUrl;
        
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(title, description, apiVersion, terms, contact, licence, licenceUrl);
        return apiInfo;
    }

}