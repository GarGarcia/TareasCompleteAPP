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
@Table(name = "teams")
public class Team {

    // Indicamos que este campo es la clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicamos que el valor de la clave primaria se generará automáticamente
    private Long id;

    // Indicamos la columna first_name de la table, es una cadena, no puede ser nulo
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "team") // Indicamos el atributo de la clase Book que se usará para la relación
    private Set<Worker> workers = new HashSet<>();
}
