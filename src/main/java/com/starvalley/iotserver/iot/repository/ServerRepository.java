package com.starvalley.iotserver.iot.repository;

import com.starvalley.iotserver.iot.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
}
