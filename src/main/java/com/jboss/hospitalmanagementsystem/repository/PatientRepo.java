package com.jboss.hospitalmanagementsystem.repository;

import com.jboss.hospitalmanagementsystem.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
