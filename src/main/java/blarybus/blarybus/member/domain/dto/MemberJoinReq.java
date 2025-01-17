package blarybus.blarybus.member.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MemberJoinReq(

    @NotBlank
    @Schema(description = "id", example = "chltmdgh517")
    String loginId,

    @NotBlank
    @Schema(description = "회원 비밀번호", example = "qkek0123")
    String password,

    @NotBlank
    @Schema(description = "회원 비밀번호 체크", example = "qkek0123")
    String password2,

    @NotBlank
    @Schema(description = "회원 이름", example = "최승호쨩")
    String name
){

}
