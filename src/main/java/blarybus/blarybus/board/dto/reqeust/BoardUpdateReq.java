package blarybus.blarybus.board.dto.reqeust;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record BoardUpdateReq (

        @Schema(description = "게시물 ID", example = "1")
        Long boardId,
        @NotBlank
        @Schema(description = "제목", example = "이것은 게시글 제목입니다.")
        String title,

        @NotBlank
        @Schema(description = "내용", example = "이것은 게시글 내용입니다.")
        String content
){

}
