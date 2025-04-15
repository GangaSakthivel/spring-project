package com.example.cage.service;

import com.example.cage.exception.ResourceNotFoundException;
import com.example.cage.model.Shop;
import com.example.cage.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

        @Autowired
        private ShopRepository shopRepository;

        public Shop getShopByName(String name) {
            return shopRepository.findByName(name)
                    .orElseThrow(() -> new ResourceNotFoundException("Shop with name " + name + " not found"));
        }
    }

