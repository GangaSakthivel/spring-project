package com.example.cage.dto;

public class ShopDTO {
    private Long id;
    private String name;
    private String location;
    private long cageCount;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getCageCount() {
        return cageCount;
    }

    public void setCageCount(long cageCount) {
        this.cageCount = cageCount;
    }
}
