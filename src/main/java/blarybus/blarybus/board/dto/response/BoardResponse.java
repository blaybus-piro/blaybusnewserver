package blarybus.blarybus.board.dto.response;

import blarybus.blarybus.board.domain.Board;
import blarybus.blarybus.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record BoardResponse(
        @NotNull
        @Schema(description = "게시물 ID", example = "1")
        Long boardId,
        @NotNull
        @Schema(description = "유저", example = "객체")
        Member member,
        @NotBlank
        @Schema(description = "게시물 제목", example = "제목입니다")
        String title,
        @NotBlank
        @Schema(description = "게시물 내용", example = "내용입니다")
        String content,

        @NotBlank
        @Schema(description = "이미지 url", example = "chltmdgh.png")
        String imageUrl,
        @NotNull
        @Schema(description = "게시물 생성 시간", example = "2024-10-10 10:10:00")
        LocalDate createdDate) {
    public static BoardResponse of(Board board) {
        return BoardResponse.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .imageUrl(board.getImageUrl())
                .createdDate(board.getCreatedDate())
                .member(board.getMember())
                .build();
    }
}

