package com.alerte.Mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.alert.entities.DomainControl;
import com.alert.entities.UserLogin;
import com.alerte.remote.UserRemote;

@ManagedBean(name = "usermbean")
@ViewScoped
public class UserMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1771364698085688932L;

	@EJB
	UserRemote userRemote;
	private DomainControl domselected;
	private List<UserLogin> ListUser = new ArrayList<UserLogin>();
	private List<UserLogin> ListUserfiltred = new ArrayList<UserLogin>();

	private UserLogin user1 = new UserLogin();
	private UserLogin user2 = new UserLogin();
	UserLogin userselected;

	public UserLogin getUserselected() {
		return userselected;
	}

	public void setUserselected(UserLogin userselected) {
		this.userselected = userselected;
	}

	public void showmsg2() {
		System.out.println(domselected.getDomain());
	}

	@PostConstruct
	public void init() {
		userselected = new UserLogin();
		user1 = new UserLogin();
		user2 = new UserLogin();
		ListUser = new ArrayList<UserLogin>();

		ListUser = userRemote.getallusers();

	}

	public void showmsg() {
		
		System.out.println("user2-----   " + user2.getLogin());
		System.out.println("userselected-----   " + userselected.getLogin());
	}

	public void print() {
		System.out.println("work");
	}

	public List<UserLogin> getListUser() {
		return ListUser;
	}

	public void setListUser(List<UserLogin> listUser) {
		ListUser = listUser;
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public void adduserto() {
		String patternPWD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		String patternEmail = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
//		if (user1.getName() == null || user1.getLogin() == null || user1.getMail() == null
//				|| user1.getPassword() == null) {
//			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Empty data");
//			PrimeFaces.current().ajax().update(":form1:growl form1 @all");
//			System.out.println("error if 1");
//		} else if (!user1.getPassword().matches(patternPWD)) {
//			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
//					"Password should contain atleast 8 characters ,1 number and 1 special character and no space");
//			PrimeFaces.current().ajax().update("form1:growl");
//			System.out.println("error if 2");
//		} else if (user1.getMail().matches(patternEmail)) {
//			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "E-mail not Valid !!!");
//			PrimeFaces.current().ajax().update("form1:growl");
//			System.out.println("error if 3");
//		} else {
		userRemote.addUser(user1);
		ListUser = userRemote.getallusers();
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "User Aded succesfuly");
		PrimeFaces.current().ajax().update("form1:growl");
		// }

	}
	
	

	public void UpdateUser() {
		userRemote.updateUser(user2);
		ListUser = userRemote.getallusers();
	}

	public void deleteuser(UserLogin u) {
		userRemote.deleteUser(u);
		ListUser = userRemote.getallusers();
	}

	public UserLogin getUser1() {
		return user1;
	}

	public void setUser1(UserLogin user1) {
		this.user1 = user1;
	}

	public UserLogin getUser2() {
		return user2;
	}

	public void setUser2(UserLogin user2) {
		this.user2 = user2;
	}

	public List<UserLogin> getListUserfiltred() {
		return ListUserfiltred;
	}

	public void setListUserfiltred(List<UserLogin> listUserfiltred) {
		ListUserfiltred = listUserfiltred;
	}

	public DomainControl getDomselected() {
		return domselected;
	}

	public void setDomselected(DomainControl domselected) {
		this.domselected = domselected;
	}

}
