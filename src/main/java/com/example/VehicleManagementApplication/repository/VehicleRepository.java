package com.example.VehicleManagementApplication.repository;

import com.example.VehicleManagementApplication.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
