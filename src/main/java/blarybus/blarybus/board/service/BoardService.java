package blarybus.blarybus.board.service;

import blarybus.blarybus.board.domain.Board;
import blarybus.blarybus.board.dto.BoardCreateReq;
import blarybus.blarybus.board.repository.BoardRepository;
import blarybus.blarybus.global.response.ResponseService;
import blarybus.blarybus.global.response.SingleResult;
import blarybus.blarybus.member.domain.Member;
import blarybus.blarybus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public SingleResult<Board> boardCreate(BoardCreateReq req, String memberId) {

        Member findMember = memberRepository.findMemberId(memberId);
        Board newBoard = Board.builder()
                .title(req.title())
                .content(req.content())
                .member(findMember).build();
        Board board = boardRepository.save(newBoard);

        return ResponseService.getSingleResult(board);
    }

}
