package com.starvalley.iotserver.iot.repository;

import com.starvalley.iotserver.iot.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Long> {

    Optional<Server> findById(Long id);
}
