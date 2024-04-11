package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Override
    Optional<Board> findById(Integer id);

    Optional<List<Board>> findAllByUserId(Integer userId);
}
