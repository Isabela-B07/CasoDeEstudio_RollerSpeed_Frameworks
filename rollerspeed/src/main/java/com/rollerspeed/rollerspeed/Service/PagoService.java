package com.rollerspeed.rollerspeed.Service;

import com.rollerspeed.rollerspeed.Model.PagoModel;
import com.rollerspeed.rollerspeed.Repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<PagoModel> listarTodos() {
        return pagoRepository.findAll();
    }

    public List<PagoModel> listarPorEstudiante(Long estudianteId) {
        return pagoRepository.findByEstudianteId(estudianteId);
    }

    public void guardar(PagoModel pago) {
        pagoRepository.save(pago);
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }

    public PagoModel buscarPorId(Long id) {
    return pagoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));
}

}
