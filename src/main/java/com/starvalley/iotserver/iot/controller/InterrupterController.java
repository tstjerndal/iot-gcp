package com.starvalley.iotserver.iot.controller;

import com.starvalley.iotserver.iot.dao.InterrupterDAO;
import com.starvalley.iotserver.iot.dao.ServerDAO;
import com.starvalley.iotserver.iot.entity.Interrupter;
import com.starvalley.iotserver.iot.entity.Interrupter;
import com.starvalley.iotserver.iot.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/interrupter")

public class InterrupterController {

    @Autowired
    InterrupterDAO interrupterDAO;

    @Autowired
    ServerDAO serverDAO;

    @PostMapping("/server/{serverid}")
    public Interrupter createInterrupter(@PathVariable(value = "serverid") Long serverid, @Valid @RequestBody Interrupter interrupter){
        Optional<Server> optionalServer = serverDAO.findById(serverid);
        Server server;
        if(!optionalServer.isPresent()){
            return null;
        } else {
            server = optionalServer.get();
        }
        interrupter.setServer(server);

        return interrupterDAO.save(interrupter);
    }

    @GetMapping("")
    public List<Interrupter> getAllInterrupters (){
        return interrupterDAO.findAll();
    }

    @GetMapping("{/server/{serverid}/interrupter/id}")
    public ResponseEntity<Interrupter> getInterrupterById (@PathVariable(value = "id") Long interrupterId){
        Optional optionalInterrupter = interrupterDAO.findById(interrupterId);

        if (!optionalInterrupter.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Interrupter interrupter = (Interrupter) optionalInterrupter.get();
            return ResponseEntity.ok(interrupter);
        }

    }
    @PutMapping("/server/{serverid}/interrupter/{id}")
    public ResponseEntity<Interrupter> updateInterrupter(@PathVariable (value = "id") Long interrupterId, @Valid @RequestBody Interrupter interrupternewValues){
        Optional<Interrupter> interrupter = interrupterDAO.find(interrupterId);
        if (!interrupter.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Interrupter updateInterrupter;
            updateInterrupter = interrupter.get();
            updateInterrupter.setName(interrupternewValues.getName());
            updateInterrupter.setDescription(interrupternewValues.getDescription());

            interrupterDAO.save(updateInterrupter);
            return ResponseEntity.ok().body(updateInterrupter);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Interrupter> deleteInterrupter (@PathVariable  (value = "id") Long interrupterId){
        Optional optionalInterrupter = interrupterDAO.findById(interrupterId);

        if (!optionalInterrupter.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Interrupter interrupter = (Interrupter) optionalInterrupter.get();
            interrupterDAO.delete(interrupter);
            return ResponseEntity.ok().build();
        }
    }
}
