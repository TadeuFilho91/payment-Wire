package com.wirecard.challenge.paymentsapi.configuration;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configure Class.
 *
 * @author Tadeu Filho
 * @see <a href="https://swagger.io/">https://swagger.io/</a>
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Returns a {@link Docket}.
     *
     * @return {@link Docket}
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.wirecard.challenge.paymentsapi.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());

        return docket;
    }

    private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder()
            .title("API Wirecard Brasil")
            .description("Wirecard Brasil - API")
            .version("1.0.0")
            .termsOfServiceUrl("API")
            .build();

        return apiInfo;
    }
}