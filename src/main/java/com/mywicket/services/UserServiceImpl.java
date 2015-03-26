package com.mywicket.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mywicket.data.UserData;

@Service
public class UserServiceImpl implements UserService {
	private List<UserData> userList = Arrays.asList(
			new UserData(0, "laryrichardson", "welcome@2014", null, null),
			new UserData(1, "mcmohanstefani", "rawiswar@87", null, null)
			);
	
	@Override
	public UserData getUserData(String userName) {
		
		for(UserData user : userList){
			if (user.getUserName().equals(userName)){
				return user;
			}
		}
		return null;
	}

}
