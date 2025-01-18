package blarybus.blarybus.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record BoardCreateReq(
        @NotBlank
        @Schema(description = "제목", example = "이것은 게시글 제목입니다.")
        String title,

        @NotBlank
        @Schema(description = "내용", example = "이것은 게시글 내용입니다.")
        String content
) {
}
