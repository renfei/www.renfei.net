package net.renfei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config implements WebMvcConfigurer {
    private final RenFeiConfig renFeiConfig;

    public Swagger3Config(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.renfei.controller.api"))
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("RenFei.Net", "https://www.renfei.net/", "i@renfei.net");
        return new ApiInfoBuilder()
                .title("RENFEI.NET 开放接口")
                .description("RenFei.NET Open API")
                .termsOfServiceUrl("https://www.renfei.net/")
                .contact(contact)
                .version(renFeiConfig.getVersion())
                .build();
    }
}
