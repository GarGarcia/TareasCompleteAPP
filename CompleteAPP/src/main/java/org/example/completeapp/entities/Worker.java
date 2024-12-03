package org.example.completeapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

// Anotaciones Lombok
@Getter
@Setter
@ToString
@NoArgsConstructor

// Anotaciones Hibernate
@Entity
@Table(name = "workers")
public class Worker {

    // Indicamos que este campo es la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicamos que el valor de la clave primaria se generará automáticamente
    private Long id;

    // Indicamos la columna first_name de la table, es una cadena, no puede ser nulo
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @ManyToOne() // Indicamos que se eliminará el libro si se elimina el autor
    @JoinColumn(name = "team_id", nullable = false) // Indicamos la columna de la tabla authors que se usará para la relación
    private Team team;

    @ManyToMany
    @JoinTable(
            name = "worker_task",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "worker_id"),  // Columna que hace referencia al autor
            inverseJoinColumns = @JoinColumn(name = "task_id")  // Columna que hace referencia a la editorial
    )
    private Set<Task> tasks = new HashSet<>();  // Relación con editoriales
}
