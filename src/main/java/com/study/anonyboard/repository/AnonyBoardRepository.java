package com.study.anonyboard.repository;

import com.study.anonyboard.entity.AnonyBoard;
import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnonyBoardRepository extends JpaRepository<AnonyBoard, Integer> {
    Optional<AnonyBoard> findById(Integer id);
    Page<AnonyBoard> findAll(Pageable pageable);
}
