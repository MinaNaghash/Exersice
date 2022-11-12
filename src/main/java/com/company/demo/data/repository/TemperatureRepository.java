package com.company.demo.data.repository;

import com.company.demo.data.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureEntity,Long> {
    @Query(value = "select e FROM #{#entityName} e where e.codeListCode = ?1")
    Optional<TemperatureEntity> findByCodeList(String codeListCode);
}
