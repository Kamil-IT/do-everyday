package com.doeveryday.doeverydaytodo.repository;

import com.doeveryday.doeverydaytodo.models.TaskMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMemberRepository extends CrudRepository<TaskMember, Long> {

    List<TaskMember> findAllByAppUsers_Id(String uuid);
}
