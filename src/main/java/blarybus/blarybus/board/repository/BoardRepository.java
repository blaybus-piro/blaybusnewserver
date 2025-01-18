package blarybus.blarybus.board.repository;

import blarybus.blarybus.board.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

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
}
