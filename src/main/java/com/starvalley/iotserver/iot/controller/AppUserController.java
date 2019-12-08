package com.starvalley.iotserver.iot.controller;

import com.starvalley.iotserver.iot.dao.AppUserDao;
import com.starvalley.iotserver.iot.entity.AppUser;
import com.starvalley.iotserver.iot.entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class AppUserController {

    @Autowired
    AppUserDao appUserDao;


    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<AppUser> listUser(){
        return appUserDao.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public AppUser create(@RequestBody AppUser user){

        System.out.println("user = " + user.getUsername());

        return appUserDao.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Sensor> delete(@PathVariable(value = "id") Long id){

        Optional optionalAppUser = appUserDao.findById(id);

        if (!optionalAppUser.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            AppUser appUser = (AppUser) optionalAppUser.get();
            appUserDao.delete(appUser);
            return ResponseEntity.ok().build();
        }
    }

}
