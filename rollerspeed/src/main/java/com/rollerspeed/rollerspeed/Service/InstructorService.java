package com.rollerspeed.rollerspeed.Service;

import com.rollerspeed.rollerspeed.Model.InstructorModel;
import com.rollerspeed.rollerspeed.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    // Listar todos los instructores
    public List<InstructorModel> listar() {
        return instructorRepository.findAll();
    }

    // Guardar un instructor
    public void guardar(InstructorModel instructor) {
        instructorRepository.save(instructor);
    }

    // Obtener un instructor por ID
    public InstructorModel obtenerPorId(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    // Eliminar un instructor por ID
    public void eliminar(Long id) {
        instructorRepository.deleteById(id);
    }
}
