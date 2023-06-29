package com.alerte.remote;

import java.util.List;

import javax.ejb.Remote;

import com.alert.entities.UserLogin;


@Remote
public interface UserRemote {

	public List<UserLogin> getallusers();

	public void addUser(UserLogin userlogin);

	public void updateUser(UserLogin userlogin);
	
	public void editUser(Integer id,UserLogin userlogin);

	public void deleteUser(UserLogin userlogin);
	public UserLogin getconnecte(String login , String passwd);
	
	public void removeUser(Integer id);

}
