package com.starvalley.iotserver.iot.dao;


import com.starvalley.iotserver.iot.entity.SensorData;
import com.starvalley.iotserver.iot.repository.SensorDataRepository;
import com.starvalley.iotserver.iot.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDataDAO {
    @Autowired
    SensorDataRepository sensorDataRepository;

    public SensorData save (SensorData sensorData){
        return sensorDataRepository.save(sensorData);
    }

    public List<SensorData> findAll (){
        return sensorDataRepository.findAll();
    }

    public Optional<SensorData> find(Long id){
        return sensorDataRepository.findById(id);
    }

    public Optional<SensorData> findOne(Long id){
        return sensorDataRepository.findOne(id);
    }

    public void delete(SensorData sensorData){
        sensorDataRepository.delete(sensorData);
    }
}
