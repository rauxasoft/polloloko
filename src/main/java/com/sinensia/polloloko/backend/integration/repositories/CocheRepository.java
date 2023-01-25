package com.sinensia.polloloko.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.polloloko.backend.business.model.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, String> {

}
