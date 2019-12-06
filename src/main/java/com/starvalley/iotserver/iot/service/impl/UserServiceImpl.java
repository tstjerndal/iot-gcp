package com.starvalley.iotserver.iot.service.impl;


import com.starvalley.iotserver.iot.dao.AppUserDao;
import com.starvalley.iotserver.iot.entity.AppUser;
import com.starvalley.iotserver.iot.entity.Server;
import com.starvalley.iotserver.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private AppUserDao userDao;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<AppUser> optionalAppUser = userDao.findByUsername(userId);
		AppUser appUser;
		if(!optionalAppUser.isPresent()){
			throw new UsernameNotFoundException("Invalid username or password.");
		} else {
			appUser = optionalAppUser.get();
		}
		return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<AppUser> findAll() {
		List<AppUser> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
    public AppUser save(AppUser user) {
        return userDao.save(user);
    }
}
