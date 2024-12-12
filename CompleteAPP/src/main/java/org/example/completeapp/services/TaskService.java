package org.example.completeapp.services;

import org.example.completeapp.entities.Task;
import org.example.completeapp.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> showOverdueTasks() {
        return taskRepository.findByDateEndingBeforeAndStatusNot(LocalDate.now(), Task.StatusTask.CERRADA);
    }

    public List<Task> showInProgressTasks() {
        return taskRepository.findByStatus(Task.StatusTask.EN_PROGRESO);
    }

    public List<Task> showOpenTasks() {
        return taskRepository.findByStatus(Task.StatusTask.ABIERTA);
    }

    public double countBugTasks() {
        return taskRepository.findByType(Task.TypeTask.BUG).size();
    }

    public double countMejoraTasks() {
        return taskRepository.findByType(Task.TypeTask.MEJORA).size();
    }

    public double countRefactorTasks() {
        return taskRepository.findByType(Task.TypeTask.REFACTORIZACION).size();
    }

    public double totalTasks(){
        return taskRepository.findAll().size();
    }

}
