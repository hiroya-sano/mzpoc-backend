package com.example.carapp.service;

import com.example.carapp.dto.HeavyDataJoinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeavyDataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<HeavyDataJoinDto> getHeavyDataByDescription(String description) {
        String sql = "SELECT m.id, m.category, m.value, d.description " +
                     "FROM heavy_main m " +
                     "JOIN heavy_detail d ON m.id = d.main_id " +
                     "WHERE d.description = '" + description + "'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new HeavyDataJoinDto(
                rs.getInt("id"),
                rs.getString("category"),
                rs.getInt("value"),
                rs.getString("description")
        ));
    }
}
