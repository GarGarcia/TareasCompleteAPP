package org.example.completeapp.controllers;


import org.example.completeapp.entities.Task;
import org.example.completeapp.services.TaskService;
import org.example.completeapp.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

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

    @PostMapping("/save")
    public String save(@ModelAttribute Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "tasks/form";
        }
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        if (task == null) {
            return "redirect:/tasks";
        }
        task.setWorkers(new HashSet<>());
        task.setTeam(null);
        model.addAttribute("workers", workerService.findAll());
        model.addAttribute("task", task);
        return "tasks/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

    // Task View
    @GetMapping("/task/{id}")
    public String showTeam(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "tasks/task";
    }

    //Filter tasks
    @GetMapping("/controlPanel/tareasRetrasadas")
    public String mostrarTareasRetrasadas(Model model) {
        model.addAttribute("tareasRetrasadas", taskService.showOverdueTasks());
        return "controlPanel/tareasRetrasadas";
    }
}
