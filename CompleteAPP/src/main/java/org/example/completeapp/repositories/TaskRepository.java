package org.example.completeapp.repositories;

import org.example.completeapp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDateEndingBeforeAndStatusNot(LocalDate dateEnding, Task.StatusTask status);
    List<Task> findByStatus(Task.StatusTask status);
    List<Task> findByType(Task.TypeTask typeTask);
}
