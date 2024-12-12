package org.example.completeapp.controllers;

import org.example.completeapp.entities.Worker;
import org.example.completeapp.services.TeamService;
import org.example.completeapp.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/workers")
public class WorkerController {
    private final WorkerService workerService;
    private final TeamService teamService;
    public WorkerController(WorkerService workerService, TeamService teamService) {
        this.workerService = workerService;
        this.teamService = teamService;
    }

    // List View
    @GetMapping
    public String listTeams (Model model){
        model.addAttribute("workers", workerService.findAll());
        return "workers/list";
    }


    // Form View
    @GetMapping("/create")
    public String createForm(Model model){
        Worker worker = new Worker();
        model.addAttribute("worker", worker);
        model.addAttribute("teams", teamService.findAll());
        return "workers/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Worker worker, BindingResult result) {
        if (result.hasErrors()) {
            return "workers/form";
        }
        workerService.save(worker);
        return "redirect:/workers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Worker worker = workerService.findById(id);
        model.addAttribute("teams", teamService.findAll());
        if (worker == null) {
            return "redirect:/workers";
        }
        model.addAttribute("worker", worker);
        return "workers/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        workerService.deleteById(id);
        return "redirect:/workers";
    }


    // Worker View
    @GetMapping("/worker/{id}")
    public String showTeam(@PathVariable Long id, Model model) {
        model.addAttribute("worker", workerService.findById(id));
        model.addAttribute("tasks", workerService.findById(id).getTasks());
        model.addAttribute("hora", LocalDate.now());
        return "workers/worker";
    }
}
