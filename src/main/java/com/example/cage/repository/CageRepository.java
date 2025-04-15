package com.example.cage.repository;

import com.example.cage.model.Cage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CageRepository extends JpaRepository<Cage, Long> {


}
