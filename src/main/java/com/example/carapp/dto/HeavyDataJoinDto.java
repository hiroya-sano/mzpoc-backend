package com.example.carapp.dto;

public class HeavyDataJoinDto {
    private int id;
    private String category;
    private int value;
    private String description;

    public HeavyDataJoinDto(int id, String category, int value, String description) {
        this.id = id;
        this.category = category;
        this.value = value;
        this.description = description;
    }

    // Getter/Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
