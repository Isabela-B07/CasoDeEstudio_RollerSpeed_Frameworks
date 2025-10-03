package com.rollerspeed.rollerspeed.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rollerspeed.rollerspeed.Model.EstudianteModel;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteModel, Long> {
}
