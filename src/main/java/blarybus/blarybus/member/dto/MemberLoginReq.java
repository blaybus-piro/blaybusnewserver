package blarybus.blarybus.member.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MemberLoginReq(

    @NotBlank
    @Schema(description = "회원 id", example = "chltmdgh517")
    String loginId,

    @NotBlank
    @Schema(description = "회원 비밀번호", example = "qkek0123")
    String password

){

}
