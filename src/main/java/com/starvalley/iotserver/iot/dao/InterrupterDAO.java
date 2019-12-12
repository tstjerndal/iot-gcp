package com.starvalley.iotserver.iot.dao;


import com.starvalley.iotserver.iot.entity.Interrupter;
import com.starvalley.iotserver.iot.repository.InterrupterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterrupterDAO {

    @Autowired
    InterrupterRepository interrupterRepository;

    public Interrupter save (Interrupter interrupter){
        return interrupterRepository.save(interrupter);
    }

    public List<Interrupter> findAll (){
        return interrupterRepository.findAll();
    }

    public Optional<Interrupter> find(Long id){
        return interrupterRepository.findById(id);
    }

    public Optional<Interrupter> findById(Long  id){
        return interrupterRepository.findById(id);
    }

    public void delete(Interrupter interrupter){
        interrupterRepository.delete(interrupter);
    }
}
