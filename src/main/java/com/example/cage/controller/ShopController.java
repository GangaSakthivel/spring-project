package com.example.cage.controller;


import com.example.cage.model.Shop;
import com.example.cage.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")

public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/search")
    public ResponseEntity<Shop> getShopByName(@RequestParam String name) {
        Shop shop = shopService.getShopByName(name);
        return ResponseEntity.ok(shop);
    }
}




