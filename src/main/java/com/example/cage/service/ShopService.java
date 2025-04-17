package com.example.cage.service;

import com.example.cage.exception.ResourceNotFoundException;
import com.example.cage.model.Shop;
import com.example.cage.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    // Create Shop
    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    // Get All Shops
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // Get Shop by ID
    public Shop getShopById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    // Update Shop
    public Shop updateShop(Long id, Shop shopDetails) {
        Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            shop.setName(shopDetails.getName());
            shop.setLocation(shopDetails.getLocation());
            return shopRepository.save(shop);
        }
        return null;
    }

    public void deleteShop(Long id) {
        if (!shopRepository.existsById(id)) {
            throw new ResourceNotFoundException("Shop not found with ID: " + id);
        }
        shopRepository.deleteById(id);
    }

}
