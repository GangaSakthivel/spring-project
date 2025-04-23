package com.example.VehicleManagementApplication.repository;

import com.example.VehicleManagementApplication.model.Document;
import com.example.VehicleManagementApplication.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByVehicle(Vehicle vehicle);
}
