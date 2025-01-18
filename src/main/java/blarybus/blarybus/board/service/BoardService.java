package blarybus.blarybus.board.service;

import blarybus.blarybus.board.domain.Board;
import blarybus.blarybus.board.dto.reqeust.BoardCreateReq;
import blarybus.blarybus.board.dto.reqeust.BoardUpdateReq;
import blarybus.blarybus.board.dto.response.BoardResponse;
import blarybus.blarybus.board.repository.BoardRepository;
import blarybus.blarybus.global.exception.CustomException;
import blarybus.blarybus.global.exception.ErrorCode;
import blarybus.blarybus.global.response.ListResult;
import blarybus.blarybus.global.response.ResponseService;
import blarybus.blarybus.global.response.SingleResult;
import blarybus.blarybus.member.domain.Member;
import blarybus.blarybus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    public BoardResponse boardUpdate(BoardUpdateReq req) {
//
//        Board board = boardRepository.updateBoard(req);
//        return BoardResponse.of(board);
//    }

    public SingleResult<Board> boardUpdate(BoardUpdateReq req) {

        Board board = boardRepository.updateBoard(req);
        return ResponseService.getSingleResult(board);
    }


    public void deleteBoard(Long boardId){
        Optional<Board> board = boardRepository.findBoard(boardId);

        if(board.isEmpty()){
            throw new CustomException(ErrorCode.BOARD_NOT_EXIST);
        }
        boardRepository.deleteBoard(boardId);
    }

    public SingleResult<BoardResponse> findBoardId(Long boardId){
        Optional<Board> board = boardRepository.findBoard(boardId);
        if(board.isEmpty()){
            throw new CustomException(ErrorCode.BOARD_NOT_EXIST);
        }

        BoardResponse boardResponse = BoardResponse.of(board.get());
        return ResponseService.getSingleResult(boardResponse);
    }


    public ListResult<Board> findAll(){
        List<Board> allBoard = boardRepository.findAll();
        return ResponseService.getListResult(allBoard);
    }
}
