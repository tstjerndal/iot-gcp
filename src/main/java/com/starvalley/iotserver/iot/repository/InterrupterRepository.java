package com.starvalley.iotserver.iot.repository;

import com.starvalley.iotserver.iot.entity.Interrupter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by tommy on 2018-04-10.
 * find sensor
 */
public interface InterrupterRepository extends JpaRepository<Interrupter, Long> {
    Optional<Interrupter> findByName(String name);

    Collection<Interrupter> findByCode(String code);
    Optional<Interrupter> findById(Long id);
}
