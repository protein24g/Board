package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Optional<Board> findById(Integer id);
    Optional<Page<Board>> findAllByUserId(Integer id, Pageable pageable);
    Page<Board> findAll(Pageable pageable);
}
