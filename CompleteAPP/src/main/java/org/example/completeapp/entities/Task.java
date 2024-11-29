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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicamos que el valor de la clave primaria se generará automáticamente
    private Long id;

    // Indicamos la columna first_name de la table, es una cadena, no puede ser nulo
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "dateOpening", nullable = false)
    private LocalDate dateOpnening;

    @Column(name = "dateEnding", nullable = false)
    private LocalDate dateEnding;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeTask type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusTask status;

    @ManyToOne(cascade = CascadeType.REMOVE) // Indicamos que se eliminará el libro si se elimina el autor
    @JoinColumn(name = "team_id", nullable = false) // Indicamos la columna de la tabla authors que se usará para la relación
    private Team team;

    @ManyToMany(mappedBy = "tasks")  // Indicamos el atributo de la clase Author que se usará para la relación
    private Set<Worker> workers = new HashSet<>();  // Atibuto de tipo Set para almacenar los autores
}