package com.example.introduce.repository;

import com.example.introduce.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Page<Board> findByTopicContaining(String topic, Pageable pageable);
    Board findByTopic(String topic);
}
