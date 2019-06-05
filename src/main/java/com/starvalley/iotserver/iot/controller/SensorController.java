package com.starvalley.iotserver.iot.controller;

import com.starvalley.iotserver.iot.dao.SensorDAO;
import com.starvalley.iotserver.iot.dao.ServerDAO;
import com.starvalley.iotserver.iot.entity.Sensor;
import com.starvalley.iotserver.iot.entity.SensorData;
import com.starvalley.iotserver.iot.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/sensor/{code}")
public class SensorController {

    @Autowired
    SensorDAO sensorDAO;

    @Autowired
    ServerDAO serverDAO;

    @PostMapping("/server/{serverid}/sensor")
    public Sensor createSensor(@PathVariable(value = "serverid") Long serverid, @Valid @RequestBody Sensor sensor){
        Optional<Server> optionalServer = serverDAO.findById(serverid);
        Server server;
        if(!optionalServer.isPresent()){
            return null;
        } else {
            server = optionalServer.get();
        }
        sensor.setServer(server);

        return sensorDAO.save(sensor);
    }

    @GetMapping("")
    public List<Sensor> getAllSensors (){
        return sensorDAO.findAll();
    }

    @GetMapping("{/id}")
    public ResponseEntity<Sensor> getSensorById (@PathVariable(value = "id") Long sensorId){
        Optional optionalSensor = sensorDAO.findById(sensorId);

        if (!optionalSensor.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Sensor sensor = (Sensor) optionalSensor.get();
            return ResponseEntity.ok(sensor);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable (value = "id") Long sensorId, @Valid @RequestBody Sensor sensornewValues){
        Optional<Sensor> sensor = sensorDAO.find(sensorId);
        if (!sensor.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Sensor updateSensor;
            updateSensor = sensor.get();
            updateSensor.setName(sensornewValues.getName());
            updateSensor.setDescription(sensornewValues.getDescription());

            sensorDAO.save(updateSensor);
            return ResponseEntity.ok().body(updateSensor);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sensor> deleteSensor (@PathVariable  (value = "id") Long sensorId){
        Optional optionalSensor = sensorDAO.findById(sensorId);

        if (!optionalSensor.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Sensor sensor = (Sensor) optionalSensor.get();
            sensorDAO.delete(sensor);
            return ResponseEntity.ok().build();
        }
    }
}
