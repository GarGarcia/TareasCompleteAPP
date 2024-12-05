package org.example.completeapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Anotaciones Lombok
@Getter
@Setter
@ToString
@NoArgsConstructor

// Anotaciones Hibernate
@Entity
@Table(name = "tasks")
public class Task {
    public enum TypeTask{
        MEJORA, BUG, REFACTORIZACION
    }

    public enum StatusTask{
        ABIERTA, EN_PROGRESO, CERRADA
    }

    // Indicamos que este campo es la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "dateOpening", nullable = false)
    private LocalDate dateOpening;

    @Column(name = "dateEnding", nullable = false)
    private LocalDate dateEnding;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeTask type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusTask status;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

    @ManyToMany
    @JoinTable(
            name = "worker_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id")
    )
    private Set<Worker> workers = new HashSet<>();
}