package ngo.friendship.projecus.config;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Zakaria Imtiaz
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
       
    @Bean
    public Docket lastApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("PROJECUS-GATEWAY")
                .apiInfo(apiInfo())
                .select()
                .paths(postPaths())
                .build();
    }
    
    private Predicate<String> postPaths(){
        return or(
                regex("/projecus/.*")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("PROJECUS-GATEWAY")
                .description("API for Projecus system")
                .version("1.0.1")
                .build();
    } 
}
