package org.example.completeapp.controllers;

import org.example.completeapp.entities.Task;
import org.example.completeapp.entities.Worker;
import org.example.completeapp.repositories.TaskRepository;
import org.example.completeapp.services.TaskService;
import org.example.completeapp.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IndexController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public IndexController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
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
    public String formularioFiltroTareas(Model model) {
        model.addAttribute("worker", new Worker()); // Valor inicial
        return "controlPanel/formularioFiltroTareas";
    }

    @PostMapping("/controlPanel/tareasEnCurso")
    public String mostrarTareasEnCurso(@ModelAttribute Task task, Model model) {
        List<Task> tareas = taskRepository.findByStatusAndWorkersId(Task.StatusTask.EN_PROGRESO, task.getId());
        model.addAttribute("tareasEnCurso", tareas);
        return "controlPanel/tareasEnCurso";
    }
}
