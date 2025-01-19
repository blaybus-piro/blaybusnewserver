package blarybus.blarybus.board.dto.reqeust;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record BoardCreateReq(
        @NotBlank
        @Schema(description = "제목", example = "이것은 게시글 제목입니다.")
        String title,

        @NotBlank
        @Schema(description = "내용", example = "이것은 게시글 내용입니다.")
        String content,


        @NotNull
        @Schema(description = "이미지 파일", type = "string", format = "binary")
        MultipartFile image
) {
}
