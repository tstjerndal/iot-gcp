package com.starvalley.iotserver.iot.entity;

import com.starvalley.iotserver.iot.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Server find(Long id){
        return serverRepository.findById(id)
    }

    public void delete(Server server){
        serverRepository.delete(server);
    }
}
