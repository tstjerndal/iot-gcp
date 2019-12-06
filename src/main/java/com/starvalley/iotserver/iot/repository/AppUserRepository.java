package com.starvalley.iotserver.iot.repository;

import com.starvalley.iotserver.iot.entity.AppUser;
import com.starvalley.iotserver.iot.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AppUserRepository  extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
