package com.busanit501.boot_project.repository;

import com.busanit501.boot_project.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
