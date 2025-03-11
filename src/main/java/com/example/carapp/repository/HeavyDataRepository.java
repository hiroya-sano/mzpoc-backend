package com.example.carapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carapp.model.HeavyData;
import java.util.List;

public interface HeavyDataRepository extends JpaRepository<HeavyData, Long> {
    List<HeavyData> findByDescriptionContainingIgnoreCase(String description);
}