package com.starvalley.iotserver.iot.service;

import com.starvalley.iotserver.iot.entity.AppUser;

import java.util.List;

public interface UserService {

    AppUser save(AppUser user);
    List<AppUser> findAll();
    void delete(long id);
}
