package com.rollerspeed.rollerspeed.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "clase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private LocalDateTime fechaHora; // Para guardar día y hora de la clase

    // Relación con Instructor
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private InstructorModel instructor;

    // Relación con Estudiantes (muchos a muchos)
    @ManyToMany
    @JoinTable(
            name = "clase_estudiante",
            joinColumns = @JoinColumn(name = "clase_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private Set<EstudianteModel> estudiantes = new HashSet<>();
}
