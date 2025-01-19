package blarybus.blarybus.board.web;

import blarybus.blarybus.board.domain.Board;
import blarybus.blarybus.board.dto.reqeust.BoardCreateReq;
import blarybus.blarybus.board.dto.reqeust.BoardUpdateReq;
import blarybus.blarybus.board.dto.response.BoardResponse;
import blarybus.blarybus.board.repository.BoardRepository;
import blarybus.blarybus.board.service.BoardService;
import blarybus.blarybus.global.response.ListResult;
import blarybus.blarybus.global.response.SingleResult;
import blarybus.blarybus.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "게시글(Board)")
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;


    @PostMapping
    @Operation(summary = "게시글 작성")
    public SuccessResponse<SingleResult<Board>> createBoard(@Valid
                                                            @RequestBody BoardCreateReq req,
                                                            @RequestAttribute("id") String memberId) {
        SingleResult<Board> result = boardService.boardCreate(req, memberId);
        return SuccessResponse.ok(result);
    }

    @PutMapping
    @Operation(summary = "게시글 업데이트")
    public SuccessResponse<SingleResult<Board>> updateBoard(@Valid
                                                            @RequestBody BoardUpdateReq req
    ) {
        // BoardResponse boardResponse = boardService.boardUpdate(req);
        SingleResult<Board> result = boardService.boardUpdate(req);
        return SuccessResponse.ok(result);
    }

    @DeleteMapping("/{boardId}")
    @Operation(summary = "게시글 삭제")
    public ResponseEntity<String> deleteBoard(@Valid @PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "게시물 단건 조회")
    public SuccessResponse<SingleResult<BoardResponse>> readBoard(@PathVariable("boardId") Long boardId) {
        SingleResult<BoardResponse> result = boardService.findBoardId(boardId);
        return SuccessResponse.ok(result);
    }


    @GetMapping
    @Operation(summary = "게시물 전체 조회")
    public SuccessResponse<ListResult<Board>> readAllBoard() {
        ListResult<Board> result = boardService.findAll();
        return SuccessResponse.ok(result);
    }


}
