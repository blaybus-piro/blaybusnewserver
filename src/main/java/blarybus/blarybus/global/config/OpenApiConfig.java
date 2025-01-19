package blarybus.blarybus.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import blarybus.blarybus.global.exception.ErrorCode; // ErrorCode import 추가

@OpenAPIDefinition(
        info = @Info(
                title = "블레이버스 명세서",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "최승호",
                        email = "chltmdgh517@naver.com"
                )
        )
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components();

        // ErrorCode에 정의된 모든 에러 코드를 Swagger Global Responses로 추가
        for (ErrorCode errorCode : ErrorCode.values()) {
            ApiResponse apiResponse = new ApiResponse()
                    .description(errorCode.getMessage());
            components.addResponses(String.valueOf(errorCode.getHttpCode()), apiResponse);
        }

        return new OpenAPI()
                .components(components);
    }
}
