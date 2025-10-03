package com.rollerspeed.rollerspeed.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.Model.ClaseModel;
import com.rollerspeed.rollerspeed.Repository.ClaseRepository;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    
    public List<ClaseModel> listar() {
        return claseRepository.findAll();
    }

    
    public void guardar(ClaseModel clase) {
        claseRepository.save(clase);
    }

    public ClaseModel buscarPorId(Long id) {
        return claseRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        claseRepository.deleteById(id);
    }
}
