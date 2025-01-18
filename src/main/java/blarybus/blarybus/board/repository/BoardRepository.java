package blarybus.blarybus.board.repository;

import blarybus.blarybus.board.domain.Board;
import blarybus.blarybus.board.dto.reqeust.BoardUpdateReq;
import blarybus.blarybus.member.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepository {

    private final EntityManager em;

    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public Board updateBoard(BoardUpdateReq req) {

        Board board = em.find(Board.class, req.boardId());
        board.setContent(req.content());
        board.setTitle(req.title());
        return board;
    }

    public void deleteBoard(Long boardId) {
        Board board = em.find(Board.class, boardId);
        em.remove(board);
    }

    public Optional<Board> findBoard(Long boardId) {
        try {
            Board findBoard = em.createQuery("select b from Board b where b.boardId = :id ", Board.class)
                    .setParameter("id", boardId)
                    .getSingleResult();
            return Optional.of(findBoard);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }
}
