package com.example.carapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carapp.model.HeavyData;

import javax.persistence.QueryHint;
import java.util.List;

public interface HeavyDataRepository extends JpaRepository<HeavyData, Long> {
    @Query(value = "WITH RECURSIVE rec AS (SELECT 1 AS n UNION ALL SELECT n + 1 FROM rec WHERE n < :row) SELECT count(*) AS count FROM rec CROSS JOIN rec AS rec2", nativeQuery = true)
    @QueryHints(@QueryHint(name = "javax.persistence.query.timeout", value = "10000"))
    List<HeavyData> findAll(int row);
}