package com.starvalley.iotserver.iot.controller;

import com.starvalley.iotserver.iot.dao.SensorDAO;
import com.starvalley.iotserver.iot.dao.SensorDataDAO;
import com.starvalley.iotserver.iot.entity.Sensor;
import com.starvalley.iotserver.iot.entity.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by tommy on 2018-04-10.
 */
@RestController
//@RequestMapping("/{name}/sensorData")
class SensorDataController {
    @Autowired
    SensorDataDAO sensorDataDAO;

    @Autowired
    SensorDAO sensorDAO;

    @PostMapping("/server/{serverid}/sensor/{sensorid}/sensorData")
    public SensorData createSensorData(@PathVariable(value = "sensorid") Long sensorid,@Valid @RequestBody SensorData sensorData){
        Optional<Sensor> optionalSensor = sensorDAO.findById(sensorid);
        Sensor sensor;
        if(!optionalSensor.isPresent()){
            return null;
        } else {
            sensor = optionalSensor.get();
        }
        sensorData.setSensor(sensor);
        return sensorDataDAO.save(sensorData);
    }

/*
    @GetMapping("/server/{serverid}/sensor/{sensorid}/sensorData")
    public List<SensorData> getAllSensorDatas (){
        return sensorDataDAO.findAll();
    }
*/
    @GetMapping("/sensorData/{sensorid}")
    public List<SensorData> getAllSensorDatasForSensor (@PathVariable(value = "serverid")Long serverid, @PathVariable(value = "sensorid") Long sensorId){
        return sensorDataDAO.findBySensorId(sensorId);
    }

    @GetMapping("/server/{serverid}/sensor/{sensorid}/sensorData/{id}")
    public ResponseEntity<SensorData> getSensorDataById (@PathVariable(value = "id") Long sensorDataId){
        Optional optionalSensorData = sensorDataDAO.findById(sensorDataId);

        if (!optionalSensorData.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            SensorData sensorData = (SensorData) optionalSensorData.get();
            return ResponseEntity.ok(sensorData);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<SensorData> updateSensorData(@PathVariable (value = "id") Long sensorDataId, @Valid @RequestBody SensorData sensorDatanewValues){
        Optional<SensorData> sensorData = sensorDataDAO.find(sensorDataId);
        if (sensorData == null){
            return ResponseEntity.notFound().build();
        } else {
            SensorData updateSensorData = sensorData.get();
            updateSensorData.setValue(sensorDatanewValues.getValue());

            sensorDataDAO.save(updateSensorData);
            return ResponseEntity.ok().body(updateSensorData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SensorData> deleteSensorData (@PathVariable  (value = "id") Long sensorDataId){
        Optional optionalSensorData = sensorDataDAO.findById(sensorDataId);

        if (!optionalSensorData.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            SensorData sensorData = (SensorData) optionalSensorData.get();
            sensorDataDAO.delete(sensorData);
            return ResponseEntity.ok().build();
        }
    }
}