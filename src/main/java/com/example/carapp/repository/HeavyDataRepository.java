package com.example.carapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carapp.model.HeavyData;

import javax.persistence.QueryHint;
import java.util.List;

public interface HeavyDataRepository extends JpaRepository<HeavyData, Long> {
    @Query(value = "WITH RECURSIVE rec (depth, id, model, custom) AS (" +
                        "SELECT 1 AS depth, id, model, custom " +
                        "FROM Car " +
                        "UNION ALL " +
                        "SELECT rec.depth + 1, " +
                                "c.id, c.model, c.custom " +
                        "FROM rec " +
                        "CROSS JOIN Car c " +
                        "WHERE rec.depth < :depth" +
                    ") " +
                    "SELECT count(*) AS count FROM rec", nativeQuery = true)
    @QueryHints(@QueryHint(name = "javax.persistence.query.timeout", value = "10000"))
    List<HeavyData> findAll(int depth);
}