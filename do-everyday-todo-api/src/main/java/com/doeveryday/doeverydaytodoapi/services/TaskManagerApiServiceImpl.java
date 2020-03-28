package com.doeveryday.doeverydaytodoapi.services;

import com.doeveryday.doeverydaytodo.models.TaskManager;
import com.doeveryday.doeverydaytodo.models.TaskMember;
import com.doeveryday.doeverydaytodo.repository.TaskManagerRepository;
import com.doeveryday.doeverydaytodo.repository.TaskMemberRepository;
import com.doeveryday.doeverydaytodoapi.api.v1.mapper.TaskManagerMapper;
import com.doeveryday.doeverydaytodoapi.api.v1.model.TaskManagerDTO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskManagerApiServiceImpl implements TaskManagerApiService {

    private final TaskManagerRepository taskManagerRepository;
    private final TaskManagerMapper taskManagerMapper;
    private final TaskMemberRepository taskMemberRepository;

    public TaskManagerApiServiceImpl(TaskManagerRepository taskManagerRepository, TaskManagerMapper taskManagerMapper, TaskMemberRepository taskMemberRepository) {
        this.taskManagerRepository = taskManagerRepository;
        this.taskManagerMapper = taskManagerMapper;
        this.taskMemberRepository = taskMemberRepository;
    }

    @Override
    public List<TaskManagerDTO> getAllTaskManager() {
        List<TaskManagerDTO> taskManagerDTOs = new ArrayList<>();
        taskManagerRepository.findAll()
                .forEach(taskManager -> taskManagerDTOs.add(
                        taskManagerMapper.taskManagerToTaskMangerDTO(taskManager)));
        return taskManagerDTOs;
    }

    @Override
    public TaskManagerDTO getTaskManagerById(Long id) throws NotFoundException {
        return taskManagerMapper.taskManagerToTaskMangerDTO(
                taskManagerRepository
                        .findById(id).orElseThrow(() -> new NotFoundException("Not found board with id: " + id)));
    }

    @Override
    public TaskManagerDTO createTaskManager(TaskManagerDTO taskManagerDTO) {
        taskManagerDTO.setId(null);
        TaskManager taskManagerToSave = taskManagerMapper.taskManagerDTOToTaskManager(taskManagerDTO);

        addMembersToTaskManager(taskManagerDTO, taskManagerToSave);

        return taskManagerMapper.taskManagerToTaskMangerDTO(
                taskManagerRepository.save(taskManagerToSave));
    }

    @Override
    public TaskManagerDTO putTaskManager(TaskManagerDTO taskManagerDTO, Long id) throws NotFoundException {
        if (id == null){
            throw new NullPointerException("Id cannot be null");
        }
        else if (!taskManagerRepository.existsById(id)){
            throw new NotFoundException("Not found task with id: " + id);
        }

        TaskManager taskManagerToSave = taskManagerMapper.taskManagerDTOToTaskManager(taskManagerDTO);
        taskManagerToSave.setId(id);
        addMembersToTaskManager(taskManagerDTO, taskManagerToSave);
        return taskManagerMapper.taskManagerToTaskMangerDTO(taskManagerRepository.save(taskManagerToSave));
    }

    @Override
    public void deleteTaskManager(Long id) {
        taskManagerRepository.deleteById(id);
    }


    private void addMembersToTaskManager(TaskManagerDTO taskManagerDTO, TaskManager taskManagerToSave) {
        List<TaskMember> taskMembers = new ArrayList<>();
        for (Long taskMemberId :
                taskManagerDTO.getTaskMembers()) {
            Optional<TaskMember> taskMemberOptional = taskMemberRepository.findById(taskMemberId);
            taskMemberOptional.ifPresent(taskMembers::add);
        }
        taskManagerToSave.setTaskMember(taskMembers);
    }
}
