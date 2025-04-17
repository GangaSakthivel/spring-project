package com.example.cage.controller;

import com.example.cage.exception.ResourceNotFoundException;
import com.example.cage.model.Shop;
import com.example.cage.service.CageService;
import com.example.cage.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private CageService cageService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/input")
    public ResponseEntity<Shop> createShop(@Valid @RequestBody Shop shop) {
        Shop savedShop = shopService.createShop(shop);
        return ResponseEntity.ok(savedShop);
    }

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Shop shop = shopService.getShopById(id);
        return shop != null ? ResponseEntity.ok(shop) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @Valid @RequestBody Shop shop) {
        Shop updatedShop = shopService.updateShop(id, shop);
        return updatedShop != null ? ResponseEntity.ok(updatedShop) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) {
        try {
            shopService.deleteShop(id);
            return ResponseEntity.ok("Shop deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting shop");
        }
    }

}
