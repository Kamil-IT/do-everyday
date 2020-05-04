package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {

    Optional<Board> findFirstByIdAndAppUsers_Username(Long id, @NotNull String appUsers_username);
}
