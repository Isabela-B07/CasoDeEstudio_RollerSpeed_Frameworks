package com.rollerspeed.rollerspeed.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "El nombre de la clase es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "Debe ingresar la fecha y hora de la clase")
    @Future(message = "La fecha y hora deben ser en el futuro")
    private LocalDateTime fechaHora;

    // Relación con Instructor
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    @NotNull(message = "Debe seleccionar un instructor")
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
