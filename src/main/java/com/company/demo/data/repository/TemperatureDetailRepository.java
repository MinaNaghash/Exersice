package com.company.demo.data.repository;

import com.company.demo.data.entity.TemperatureDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemperatureDetailRepository extends JpaRepository<TemperatureDetailEntity,String> {
    Optional<TemperatureDetailEntity> findByCode(String code);
}
