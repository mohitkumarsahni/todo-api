package com.sahni.todoapi.repositories;

import com.sahni.todoapi.models.TaskLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskListsRepository extends JpaRepository<TaskLists, UUID> {

    Optional<TaskLists> findByName(String name);
}
