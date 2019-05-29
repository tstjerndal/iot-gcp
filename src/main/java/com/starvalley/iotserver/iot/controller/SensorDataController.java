package com.starvalley.iotserver.iot.controller;

import com.starvalley.iotserver.iot.entity.Sensor;
import com.starvalley.iotserver.iot.entity.SensorData;
import com.starvalley.iotserver.iot.repository.SensorDataRepository;
import com.starvalley.iotserver.iot.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Created by tommy on 2018-04-10.
 */
@RestController
@RequestMapping("/{name}/sensorData")
class SensorDataController {
    private final SensorRepository sensorRepository;
    private final SensorDataRepository sensorDataRepository;


    @Autowired
    SensorDataController(SensorDataRepository sensorDataRepository,SensorRepository sensorRepository) {
        this.sensorDataRepository = sensorDataRepository;
        this.sensorRepository = sensorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<SensorData> readSensor(@PathVariable String name) throws Exception {
        this.validateSensor(name);
        return this.sensorDataRepository.findBySensorName(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String name, @RequestBody Sensor input) throws Exception {
        this.validateSensor(name);

        return this.sensorRepository.findByName(name).map(sensor -> {
            SensorData result = sensorDataRepository.save(new SensorData(sensor,new Date(), input.getId()));

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();

            return ResponseEntity.created(location).build();
        })
                .orElse(ResponseEntity.noContent().build());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sensorDataId}")
    Optional<SensorData> readSensorData(@PathVariable String name, @PathVariable Long sensorDataId) throws Exception {
        this.validateSensor(name);

        Optional<SensorData> sensorData = this.sensorDataRepository.findById(sensorDataId);
        if (sensorData.isPresent()) {
            return sensorData;
        } else return null;
    }

    private void validateSensor(String sensorName) throws Exception {
        this.sensorRepository.findByName(sensorName).orElseThrow(
                () -> new Exception("Error finding sensro"+sensorName));
    }
}