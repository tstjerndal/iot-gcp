package com.starvalley.iotserver.iot.dao;

import com.starvalley.iotserver.iot.entity.Server;
import com.starvalley.iotserver.iot.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerDAO {

    @Autowired
    ServerRepository serverRepository;

    public Server save (Server server){
        return serverRepository.save(server);
    }

    public List <Server> findAll (){
        return serverRepository.findAll();
    }

    public Optional<Server> find(Long id){
        return serverRepository.findById(id);
    }

    public Optional<Server> findOne(Long id){
        return serverRepository.findOne(id);
    }

    public void delete(Server server){
        serverRepository.delete(server);
    }
}
