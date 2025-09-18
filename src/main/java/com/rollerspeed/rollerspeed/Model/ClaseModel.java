package com.rollerspeed.rollerspeed.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clase")
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

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public InstructorModel getInstructor() { return instructor; }
    public void setInstructor(InstructorModel instructor) { this.instructor = instructor; }

    public Set<EstudianteModel> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Set<EstudianteModel> estudiantes) { this.estudiantes = estudiantes; }
}
