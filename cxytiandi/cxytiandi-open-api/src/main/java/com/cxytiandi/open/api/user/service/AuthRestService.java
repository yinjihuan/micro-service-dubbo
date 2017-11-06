package com.cxytiandi.open.api.user.service;

import com.cxytiandi.model.user.po.User;

public interface AuthRestService {

	public User getUser(String ak, String sk);
	
}
