package ar.com.codoacodo.spring.swagger;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().securityContexts(securityContext()).securitySchemes(securitySchemes())
				.useDefaultResponseMessages(false)
				// seteo interfaz ui swagger para errores
				.globalResponses(HttpMethod.GET,
						List.of(new ResponseBuilder().code("200").description("200 OK!!!").build(),
								new ResponseBuilder().code("201").description("201 CREATED").build(),
								new ResponseBuilder().code("204").description("204 NO CONTENT").build(),
								new ResponseBuilder().code("400").description("400 BAD REQUEST").build(),
								new ResponseBuilder().code("401").description("401 UNAUTHORIZED!!!").build(),
								new ResponseBuilder().code("403").description("Forbidden!!!!!").build(),
								new ResponseBuilder().code("404").description("404 NOT FOUND!!!!!").build(),
								new ResponseBuilder().code("405").description("405 METHOD NOT ALLOWED!!!!!").build(),
								new ResponseBuilder().code("406").description("406 NOT ACCEPTABLE!!!!!").build(),
								new ResponseBuilder().code("409").description("409 CONFLICT!!!").build(),
								new ResponseBuilder().code("415").description("415 UNSUPPORTED MEDIA TYPE!!").build(),
								new ResponseBuilder().code("500").description("500 INTERNAL SERVER ERROR!!").build()))
				.globalResponses(HttpMethod.POST,
						List.of(new ResponseBuilder().code("200").description("200 OK!!!").build(),
								new ResponseBuilder().code("201").description("201 CREATED").build(),
								new ResponseBuilder().code("204").description("204 NO CONTENT").build(),
								new ResponseBuilder().code("400").description("400 BAD REQUEST").build(),
								new ResponseBuilder().code("401").description("401 UNAUTHORIZED!!!").build(),
								new ResponseBuilder().code("403").description("Forbidden!!!!!").build(),
								new ResponseBuilder().code("404").description("404 NOT FOUND!!!!!").build(),
								new ResponseBuilder().code("405").description("405 METHOD NOT ALLOWED!!!!!").build(),
								new ResponseBuilder().code("406").description("406 NOT ACCEPTABLE!!!!!").build(),
								new ResponseBuilder().code("409").description("409 CONFLICT!!!").build(),
								new ResponseBuilder().code("415").description("415 UNSUPPORTED MEDIA TYPE!!").build(),
								new ResponseBuilder().code("500").description("500 INTERNAL SERVER ERROR!!").build()))
				.globalResponses(HttpMethod.PUT,
						List.of(new ResponseBuilder().code("200").description("200 OK!!!").build(),
								new ResponseBuilder().code("201").description("201 CREATED").build(),
								new ResponseBuilder().code("204").description("204 NO CONTENT").build(),
								new ResponseBuilder().code("400").description("400 BAD REQUEST").build(),
								new ResponseBuilder().code("401").description("401 UNAUTHORIZED!!!").build(),
								new ResponseBuilder().code("403").description("Forbidden!!!!!").build(),
								new ResponseBuilder().code("404").description("404 NOT FOUND!!!!!").build(),
								new ResponseBuilder().code("405").description("405 METHOD NOT ALLOWED!!!!!").build(),
								new ResponseBuilder().code("406").description("406 NOT ACCEPTABLE!!!!!").build(),
								new ResponseBuilder().code("409").description("409 CONFLICT!!!").build(),
								new ResponseBuilder().code("415").description("415 UNSUPPORTED MEDIA TYPE!!").build(),
								new ResponseBuilder().code("500").description("500 INTERNAL SERVER ERROR!!").build()))
				.globalResponses(HttpMethod.DELETE,
						List.of(new ResponseBuilder().code("200").description("200 OK!!!").build(),
								new ResponseBuilder().code("201").description("201 CREATED").build(),
								new ResponseBuilder().code("204").description("204 NO CONTENT").build(),
								new ResponseBuilder().code("400").description("400 BAD REQUEST").build(),
								new ResponseBuilder().code("401").description("401 UNAUTHORIZED!!!").build(),
								new ResponseBuilder().code("403").description("Forbidden!!!!!").build(),
								new ResponseBuilder().code("404").description("404 NOT FOUND!!!!!").build(),
								new ResponseBuilder().code("405").description("405 METHOD NOT ALLOWED!!!!!").build(),
								new ResponseBuilder().code("406").description("406 NOT ACCEPTABLE!!!!!").build(),
								new ResponseBuilder().code("409").description("409 CONFLICT!!!").build(),
								new ResponseBuilder().code("415").description("415 UNSUPPORTED MEDIA TYPE!!").build(),
								new ResponseBuilder().code("500").description("500 INTERNAL SERVER ERROR!!").build()));

	}

	private List<SecurityScheme> securitySchemes() {
		ApiKey jwt = new ApiKey("JWT", "Authorization", "header");
		return Collections.singletonList(jwt);
	}

	public List<SecurityContext> securityContext() {
		SecurityContext securityContext = SecurityContext.builder().securityReferences(defautlAuth()).build();
		return Collections.singletonList(securityContext);
	}

	private List<SecurityReference> defautlAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverithing");
		AuthorizationScope[] authorizationsScopes = new AuthorizationScope[] { authorizationScope };
		SecurityReference securityReference = new SecurityReference("JWT", authorizationsScopes);
		return Collections.singletonList(securityReference);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.contact(new Contact("Lionel Staricoff", "https://github.com/LionelStaricoff/api-rest-spring-boot",
						"staricofflionel@hotmail.com"))
				.description("Proyecto Rest final, Bootcamp Codo a Codo").title("SpringBoot + JWT + Swagger ")
				.version("1.0.0-FINAL").build();
	}
}