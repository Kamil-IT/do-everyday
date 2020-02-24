package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
}
