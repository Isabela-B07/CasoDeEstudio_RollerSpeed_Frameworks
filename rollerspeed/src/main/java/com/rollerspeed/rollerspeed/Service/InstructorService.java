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

    public List<InstructorModel> listar() {
        return instructorRepository.findAll();
    }

    public void guardar(InstructorModel instructor) {
        instructorRepository.save(instructor);
    }

    public InstructorModel buscarPorId(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        instructorRepository.deleteById(id);
    }
}
