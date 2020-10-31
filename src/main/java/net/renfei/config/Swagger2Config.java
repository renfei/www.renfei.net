package net.renfei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config implements WebMvcConfigurer {
    private final RenFeiConfig renFeiConfig;

    public Swagger2Config(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.renfei.controller.api"))
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("RenFei.Net", "http://www.renfei.net/", "i@renfei.net");
        return new ApiInfoBuilder()
                .title("RENFEI.NET 开放接口")
                .description("RenFei.NET Open API")
                .termsOfServiceUrl("http://www.renfei.net/")
                .contact(contact)
                .version(renFeiConfig.getVersion())
                .build();
    }

    /**
     * swagger-ui.html路径映射，浏览器中使用/api-docs访问
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/open/docs", "/swagger-ui.html");
    }

}
