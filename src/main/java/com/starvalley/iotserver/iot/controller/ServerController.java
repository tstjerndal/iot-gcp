package com.starvalley.iotserver.iot.controller;

import com.starvalley.iotserver.iot.dao.ServerDAO;
import com.starvalley.iotserver.iot.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        Server server = serverDAO.find(serverId);

        if (server == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(server);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Server> updateServer(@PathVariable (value = "id") Long serverId, @Valid @RequestBody Server serverUpdate){
        Server server = serverDAO.find(serverId);
        if (server == null){
            return ResponseEntity.notFound().build();
        } else {
            server.setName(serverUpdate.getName());
            server.setDescription(serverUpdate.getDescription());

            serverDAO.save(server);
            return ResponseEntity.ok().body(server);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Server> deleteServer (@PathVariable  (value = "id") Long serverId){
        Server server = serverDAO.find(serverId);

        if (server== null){
            return ResponseEntity.notFound().build();
        } else {
            serverDAO.delete(server);
            return ResponseEntity.ok().build();
        }
    }
}
