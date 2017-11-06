package com.cxytiandi.open.api.user.service;

import org.springframework.stereotype.Service;

import com.cxytiandi.model.user.po.User;

@Service
public class AuthRestServiceImpl implements AuthRestService {
	
	@Override
	public User getUser(String ak, String sk) {
		User u = new User();
		u.setId("1001");
		return u;
	}

}
