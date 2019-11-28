package com.starvalley.iotserver.iot.controller;

import com.starvalley.iotserver.iot.dao.ServerDAO;
import com.starvalley.iotserver.iot.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    ServerDAO serverDAO;

    @PostMapping("/")
    public Server createServer(@Valid @RequestBody Server server){
        return serverDAO.save(server);
    }

    @GetMapping("")
    public List<Server> getAllServers (){
        return serverDAO.findAll();
    }

    @GetMapping("{/id}")
    public ResponseEntity<Server> getServerById (@PathVariable(value = "id") Long serverId){
        Optional optionalServer = serverDAO.findById(serverId);

        if (!optionalServer.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Server server = (Server) optionalServer.get();
            return ResponseEntity.ok(server);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Server> updateServer(@PathVariable (value = "id") Long serverId, @Valid @RequestBody Server servernewValues){
        Optional<Server> server = serverDAO.find(serverId);
        if (server == null){
            return ResponseEntity.notFound().build();
        } else {
            Server updateServer = server.get();
            updateServer.setName(servernewValues.getName());
            updateServer.setDescription(servernewValues.getDescription());
            updateServer.setCode(servernewValues.getCode());
            updateServer.setFavorite(servernewValues.getIesFavorite());
            updateServer.setImageUrl(servernewValues.getImageUrl());
            updateServer.setLocation( servernewValues.getLocation());


            serverDAO.save(updateServer);
            return ResponseEntity.ok().body(updateServer);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Server> patchServer(@PathVariable (value = "id") Long serverId, @Valid @RequestBody Server servernewValues){
        Optional<Server> server = serverDAO.find(serverId);
        if (server == null){
            return ResponseEntity.notFound().build();
        } else {
            Server updateServer = server.get();
            updateServer.setName(servernewValues.getName());
            updateServer.setDescription(servernewValues.getDescription());

            serverDAO.save(updateServer);
            return ResponseEntity.ok().body(updateServer);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Server> deleteServer (@PathVariable  (value = "id") Long serverId){
        Optional optionalServer = serverDAO.findById(serverId);

        if (!optionalServer.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Server server = (Server) optionalServer.get();
            serverDAO.delete(server);
            return ResponseEntity.ok().build();
        }
    }
}
