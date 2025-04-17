package com.example.cage.service;

import com.example.cage.model.Cage;
import com.example.cage.repository.CageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CageService {

    @Autowired
    private CageRepository cageRepository;

    // Create a new cage
    public Cage createCage(Cage cage) {
        return cageRepository.save(cage);  // Save the cage to the database
    }

    // Get all cages
    public List<Cage> getAllCages() {
        return cageRepository.findAll();  // Retrieve all cages
    }

    public long getTotalQuantityByShopName(String shopName) {
        return cageRepository.getTotalQuantityByShopName(shopName);
    }


    // Update a cage
    public Cage updateCage(Long id, Cage cageDetails) {
        Optional<Cage> cageOptional = cageRepository.findById(id);
        if (cageOptional.isPresent()) {
            Cage existingCage = cageOptional.get();
            existingCage.setName(cageDetails.getName());
            existingCage.setQuantity(cageDetails.getQuantity());
            existingCage.setLocation(cageDetails.getLocation());
            existingCage.setStatus(cageDetails.getStatus());
            existingCage.setShop(cageDetails.getShop());  // Update shop info if changed
            return cageRepository.save(existingCage);  // Save the updated cage
        }
        return null;  // If the cage wasn't found
    }

    public String deleteCage(Long id) {
        // Check if the cage exists
        Optional<Cage> cageOptional = cageRepository.findById(id);
        if (cageOptional.isPresent()) {
            cageRepository.deleteById(id);  // If found, delete the cage
            return "Cage deleted successfully";
        } else {
            return "Cage not found with ID: " + id;  // If not found, return an error message
        }
    }
}
