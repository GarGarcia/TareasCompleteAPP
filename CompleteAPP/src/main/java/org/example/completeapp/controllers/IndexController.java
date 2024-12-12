package org.example.completeapp.controllers;

import org.example.completeapp.entities.Task;
import org.example.completeapp.entities.Worker;
import org.example.completeapp.repositories.TaskRepository;
import org.example.completeapp.services.TaskService;
import org.example.completeapp.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Controller
public class IndexController {
    private final TaskService taskService;
    private final WorkerService workerService;

    public IndexController(TaskService taskService, WorkerService workerService) {
        this.taskService = taskService;
        this.workerService = workerService;
    }

    // La ruta para mostrar la página de inicio /
    @GetMapping("/")
    public String index() {
        return "index"; // Devolvemos el nombre de la plantilla, en este caso index.html
    }

    @GetMapping("/controlPanel")
    public String controlPanel() {
        return "controlPanel/controlPanel";
    }

    //Filter tasks
    @GetMapping("/controlPanel/tareasRetrasadas")
    public String mostrarTareasRetrasadas(Model model) {
        model.addAttribute("tareasRetrasadas", taskService.showOverdueTasks());
        return "controlPanel/tareasRetrasadas";
    }

    @GetMapping("/controlPanel/tareasEnCurso")
    public String mostrarTareasEnCurso(Model model) {
        model.addAttribute("tasks", taskService.showInProgressTasks());
        return "controlPanel/tareasEnCurso";
    }

    @GetMapping("/controlPanel/tareasAbiertas")
    public String mostrarTareasAbiertas(Model model) {
        List<Task> listaOrdenada = taskService.showOpenTasks();
        listaOrdenada.sort(Comparator.comparing(Task::getDateOpening));
        model.addAttribute("tasks", listaOrdenada);
        model.addAttribute("hora", LocalDate.now());
        return "controlPanel/tareasAbiertas";
    }

    @GetMapping("/controlPanel/numeroDeTareas")
    public String mostrarNumeroTareas(Model model) {
        double numBug = taskService.countBugTasks();
        double numMejora = taskService.countMejoraTasks();
        double numRefactor = taskService.countRefactorTasks();

        double totalTasks = taskService.totalTasks();

        model.addAttribute("numBug", (int) numBug);
        model.addAttribute("numMejora", (int) numMejora);
        model.addAttribute("numRefactor", (int) numRefactor);

        double porcentajeBug = numBug / totalTasks * 100;
        double porcentajeMejora = numMejora / totalTasks * 100;
        double porcentajeRefactor = numRefactor / totalTasks * 100;

        model.addAttribute("porcentajeBug", porcentajeBug);
        model.addAttribute("porcentajeMejora", porcentajeMejora);
        model.addAttribute("porcentajeRefactor", porcentajeRefactor);

        return "controlPanel/numeroTareas";
    }
}
