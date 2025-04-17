package com.example.cage.controller;

import com.example.cage.dto.CageDTO;
import com.example.cage.model.Cage;
import com.example.cage.service.CageService;
import com.example.cage.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cage")
public class CageController {

    @Autowired
    private CageService cageService;

    @Autowired
    private ShopService shopService;

    //new cage
    @PostMapping("/input")
    public ResponseEntity<?> createCage(@RequestBody CageDTO cageDTO) {
        try {
            // Check if the shop exists
            if (shopService.getShopById(cageDTO.getShopId()) == null) {
                return ResponseEntity.status(400).body("Shop not found with ID: " + cageDTO.getShopId());
            }

            Cage cage = new Cage();
            cage.setName(cageDTO.getName());
            cage.setQuantity(cageDTO.getQuantity());
            cage.setLocation(cageDTO.getLocation());
            cage.setStatus(cageDTO.getStatus());
            cage.setShop(shopService.getShopById(cageDTO.getShopId()));

            cage = cageService.createCage(cage);  // Save the cage

            return ResponseEntity.ok(cage);
        } catch (Exception e) {
            e.printStackTrace();  // For debugging
            return ResponseEntity.status(500).body("Error while saving the cage: " + e.getMessage());
        }
    }

    // Get all cages
    @GetMapping("/all")
    public ResponseEntity<List<Cage>> getAllCages() {
        List<Cage> cages = cageService.getAllCages();
        return ResponseEntity.ok(cages);
    }


    // Update a cage
    @PutMapping("/{id}")
    public ResponseEntity<Cage> updateCage(@PathVariable Long id, @RequestBody CageDTO cageDTO) {
        try {
            // Check if the shop exists
            if (shopService.getShopById(cageDTO.getShopId()) == null) {
                return ResponseEntity.status(400).body(null);  // Shop not found
            }

            // Convert the CageDTO to a Cage entity
            Cage cageDetails = new Cage();
            cageDetails.setName(cageDTO.getName());
            cageDetails.setQuantity(cageDTO.getQuantity());
            cageDetails.setLocation(cageDTO.getLocation());
            cageDetails.setStatus(cageDTO.getStatus());
            cageDetails.setShop(shopService.getShopById(cageDTO.getShopId()));

            Cage updatedCage = cageService.updateCage(id, cageDetails);

            if (updatedCage == null) {
                return ResponseEntity.status(404).body(null);  // Cage not found
            }

            return ResponseEntity.ok(updatedCage);  // Return the updated cage in the response
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);  // Handle internal server error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCage(@PathVariable Long id) {
        // Call the deleteCage method from the service
        String responseMessage = cageService.deleteCage(id);

        // If the cage wasn't found, return a 404 error
        if (responseMessage.contains("not found")) {
            return ResponseEntity.status(404).body(responseMessage);
        }

        return ResponseEntity.ok(responseMessage);  // Return success message
    }

    @GetMapping("/{shopName}/count")
    public ResponseEntity<String> getCageQuantityByShopName(@PathVariable String shopName) {
        long quantity = cageService.getTotalQuantityByShopName(shopName);
        return ResponseEntity.ok("Cage count = " + quantity);
    }

}

//The CageController is responsible for handling requests
// like "get cage count by shop name" and interacting with both the Shop and Cage entities.