package com.example.carapp.model;

import javax.persistence.*;

@Entity
public class HeavyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long count;

    // getter / setter
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
}
