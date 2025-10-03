package com.rollerspeed.rollerspeed.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false, length = 50)
    private String apellido;

    @Past(message = "La fecha debe ser en el pasado")
    private java.time.LocalDate fechaNacimiento;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @Email(message = "Debe ser un correo válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos")
    private String telefono;

    @NotBlank(message = "El medio de pago es obligatorio")
    @Column(nullable = false)
    private String medioPago;
}
