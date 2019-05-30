package com.starvalley.iotserver.iot.dao;


import com.starvalley.iotserver.iot.entity.Sensor;
import com.starvalley.iotserver.iot.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDAO {
    @Autowired
    SensorRepository sensorRepository;

    public Sensor save (Sensor sensor){
        return sensorRepository.save(sensor);
    }

    public List<Sensor> findAll (){
        return sensorRepository.findAll();
    }

    public Optional<Sensor> find(Long id){
        return sensorRepository.findById(id);
    }

    public Optional<Sensor> findOne(Long id){
        return sensorRepository.findOne(id);
    }

    public void delete(Sensor sensor){
        sensorRepository.delete(sensor);
    }
}
