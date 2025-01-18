package blarybus.blarybus.board.web;

import blarybus.blarybus.board.domain.Board;
import blarybus.blarybus.board.dto.BoardCreateReq;
import blarybus.blarybus.board.service.BoardService;
import blarybus.blarybus.global.response.SingleResult;
import blarybus.blarybus.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public SuccessResponse<SingleResult<Board>> createBoard(@Valid
                                                            @RequestAttribute("id") String memberId,
                                                            BoardCreateReq req) {
        SingleResult<Board> result = boardService.boardCreate(req, memberId);
        return SuccessResponse.ok(result);
    }
}
