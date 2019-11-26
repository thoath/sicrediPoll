package br.com.sicredi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuracao da documentacao da aoi utilizando Swagger 2
 * @author lucaskoch
 * @see https://swagger.io/docs/specification/2-0/what-is-swagger/
 * @see {api_url}/swagger-ui.html#/ para acessar a documentacao da api
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
   @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("br.com.sicredi"))
                .paths(PathSelectors.any())
                .build();
    }

   
}
