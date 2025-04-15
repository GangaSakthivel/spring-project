package com.example.cage.service;

import com.example.cage.exception.ResourceNotFoundException;
import com.example.cage.model.Cage;
import com.example.cage.repository.CageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CageService {

    @Autowired
    private CageRepository cageRepository;


    public String saveCage(Cage cage) throws DatabaseOperationException {
        try {
            cageRepository.save(cage);
            return "Saved";
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseOperationException("Constraint violation: " + ex.getMessage());
        } catch (DataAccessException ex) {
            throw new DatabaseOperationException("Database error: " + ex.getMessage());
        }
    }

    public List<Cage> getAllCages() {
        return cageRepository.findAll();
    }

    public String updateCage(Cage cage) {
        cageRepository.save(cage);
        return "Updated";
    }


    public String deleteCage(Long id) {
        if (!cageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cage with ID " + id + " not found");
        }

        cageRepository.deleteById(id);
        return "Deleted";
    }

}
