package com.example.VehicleManagementApplication.controller;


import com.example.VehicleManagementApplication.DTO.VehicleRequestDTO;
import com.example.VehicleManagementApplication.DTO.VehicleResponseDTO;
import com.example.VehicleManagementApplication.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.createVehicle(vehicleRequestDTO);
        return ResponseEntity.ok(vehicleResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
        List<VehicleResponseDTO> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable Long id) {
        VehicleResponseDTO vehicleResponseDTO = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicleResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable Long id,
                                                            @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO updatedVehicle = vehicleService.updateVehicle(id, vehicleRequestDTO);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }




}
