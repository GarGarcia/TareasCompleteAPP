package org.example.completeapp.services;

import org.example.completeapp.entities.Team;
import org.example.completeapp.entities.Worker;
import org.example.completeapp.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    public void deleteById(Long id) {
        workerRepository.deleteById(id);
    }
}
