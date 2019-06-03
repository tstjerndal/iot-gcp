package com.starvalley.iotserver.iot.repository;

import com.starvalley.iotserver.iot.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by tommy on 2018-04-10.
 * find sensor
 */
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByName(String name);

    Collection<Sensor> findByCode(String code);
    Optional<Sensor> findById(Long id);
}
