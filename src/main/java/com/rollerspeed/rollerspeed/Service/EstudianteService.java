package com.rollerspeed.rollerspeed.Service;

import com.rollerspeed.rollerspeed.Model.EstudianteModel;
import com.rollerspeed.rollerspeed.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<EstudianteModel> listar() {
        return estudianteRepository.findAll();
    }

    public void guardar(EstudianteModel estudiante) {
        estudianteRepository.save(estudiante);
    }

    
    public EstudianteModel obtenerPorId(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }
}
