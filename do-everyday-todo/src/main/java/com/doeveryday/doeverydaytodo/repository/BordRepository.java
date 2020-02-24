package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.Bord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BordRepository extends CrudRepository<Bord, Long> {
}
