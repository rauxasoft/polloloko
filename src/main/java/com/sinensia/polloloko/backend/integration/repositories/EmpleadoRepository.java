package com.sinensia.polloloko.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.polloloko.backend.business.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
