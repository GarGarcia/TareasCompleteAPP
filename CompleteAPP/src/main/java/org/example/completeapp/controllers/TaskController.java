package org.example.completeapp.controllers;


import org.example.completeapp.entities.Task;
import org.example.completeapp.entities.Worker;
import org.example.completeapp.services.TaskService;
import org.example.completeapp.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final WorkerService workerService;

    public TaskController(TaskService taskService, WorkerService workerService) {
        this.taskService = taskService;
        this.workerService = workerService;
    }

    // List View
    @GetMapping
    public String listTeams (Model model){
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/list";
    }

    // Form View
    @GetMapping("/create")
    public String createForm(Model model){
        Task task = new Task();
        model.addAttribute("task", task);
        model.addAttribute("workers", workerService.findAll());
        return "tasks/form";
    }
}
