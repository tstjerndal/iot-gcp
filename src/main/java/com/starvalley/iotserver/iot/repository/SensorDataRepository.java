package com.starvalley.iotserver.iot.repository;

import com.starvalley.iotserver.iot.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by tommy on 2018-04-10.
 */
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    Collection<SensorData> findBySensorName(String name);
    Optional<SensorData> findById(Long id);
}