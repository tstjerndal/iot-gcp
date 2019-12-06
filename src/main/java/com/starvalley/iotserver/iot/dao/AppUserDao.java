package com.starvalley.iotserver.iot.dao;

import com.starvalley.iotserver.iot.entity.AppUser;
import com.starvalley.iotserver.iot.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDao {

    @Autowired
    AppUserRepository appUserRepository ;

    public Optional<AppUser> findByUsername(String username){
        return appUserRepository.findByUsername(username);
    }
    public List<AppUser> findAll (){
        return appUserRepository.findAll();
    }

    public AppUser save(AppUser user) {
        return appUserRepository.save(user);

    }

    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

    public void delete(Long  id) {
        Optional<AppUser> optionalAppUser = findById(id);
        AppUser appUser;
        if(!optionalAppUser.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
            appUser = optionalAppUser.get();
        }
        appUserRepository.delete(appUser);
    }
}
