package com.example.cage.controller;

import com.example.cage.model.Cage;
import com.example.cage.service.CageService;
import com.example.cage.service.DatabaseOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cage")
public class CageController {

    @Autowired
    private CageService cageService;

    @PostMapping("/input")
    public String input(@RequestBody Cage cage) throws DatabaseOperationException {
        return cageService.saveCage(cage);
    }

    @GetMapping("/all")
    public List<Cage> allCages(){
        return cageService.getAllCages();
    }

    @PutMapping("/update/{id}")
    public String updateCage(@PathVariable Long id, @RequestBody Cage cage) {
        return cageService.updateCage(cage);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCage(@PathVariable Long id){
        return cageService.deleteCage(id);
    }

}
