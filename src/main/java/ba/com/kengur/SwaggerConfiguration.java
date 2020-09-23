package ba.com.kengur;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static ApiInfo apiInfo() {
        ApiInfoBuilder builder = new ApiInfoBuilder();
        Contact contact = new Contact("Kengur", "WIP", "WIP");
        builder.contact(contact);
        builder.version("1.0.0");
        builder.title("Kengur Back API");
        return builder.build();
    }
}