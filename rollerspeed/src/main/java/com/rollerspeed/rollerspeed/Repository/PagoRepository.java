package com.rollerspeed.rollerspeed.Repository;

import com.rollerspeed.rollerspeed.Model.PagoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Long> {
    List<PagoModel> findByEstudianteId(Long estudianteId);
}
