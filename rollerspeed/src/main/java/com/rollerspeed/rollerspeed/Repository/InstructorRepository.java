package com.rollerspeed.rollerspeed.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rollerspeed.rollerspeed.Model.InstructorModel;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorModel, Long> {
}
