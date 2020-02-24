package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.TaskMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMemberRepository extends CrudRepository<TaskMember, Long> {
}
