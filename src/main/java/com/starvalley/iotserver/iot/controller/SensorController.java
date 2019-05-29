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

@RestController
@RequestMapping("/sensor/{code}")
public class SensorController {
    private final SensorRepository sensorRepository;


    @Autowired
    SensorController(SensorDataRepository sensorDataRepository,SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Sensor> readSensor(@PathVariable String code) throws Exception {
        this.validateSensor(code);
        return this.sensorRepository.findByCode(code);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String code, @RequestBody Sensor input) throws Exception {
        this.validateSensor(code);

        return this.sensorRepository.findByCode(code).map(sensor -> {

            Sensor result = sensorRepository.save(new Sensor(input.getName(), input.getCode(), input.getServeName()));

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();

            return ResponseEntity.created(location).build();
        })
                .orElse(ResponseEntity.noContent().build());

    }
    private void validateSensor(String sensorName) throws Exception {
        this.sensorRepository.findByName(sensorName).orElseThrow(
                () -> new Exception("Error finding sensro"+sensorName));
    }
}
