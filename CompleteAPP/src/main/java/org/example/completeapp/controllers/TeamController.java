package org.example.completeapp.controllers;

import org.example.completeapp.entities.Team;
import org.example.completeapp.services.TeamService;
import org.example.completeapp.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final WorkerService workerService;

    public TeamController(TeamService teamService, WorkerService workerService) {
        this.teamService = teamService;
        this.workerService = workerService;
    }

    @GetMapping
    public String listTeams (Model model){
        model.addAttribute("teams", teamService.findAll());
        return "teams/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        Team team = new Team();
        model.addAttribute("team", team);
        return "teams/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Team team, BindingResult result) {
        if (result.hasErrors()) {
            return "teams/form";
        }
        teamService.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Team team = teamService.findById(id);
        if (team == null) {
            return "redirect:/teams";
        }
        model.addAttribute("team", team);
        return "teams/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        teamService.deleteById(id);
        return "redirect:/teams";
    }

    @GetMapping("/team/{id}")
    public String showTeam(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.findById(id));
        return "teams/team";
    }
}
