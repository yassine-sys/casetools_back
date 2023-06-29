package com.alerte.Mbean;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.primefaces.PrimeFaces;

import com.alert.entities.UserLogin;
import com.alerte.remote.UserRemote;
import com.alert.entities.Util;

@SessionScoped
@ManagedBean(name = "loginbean")
public class LoginMbean implements Serializable {

	@EJB
	UserRemote userRemote;

	private UserLogin user = new UserLogin();

	private String login = "";
	private String passwd = "";

	@PostConstruct
	public void init() {
		user = new UserLogin();
		login = "";
		passwd = "";
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	HttpSession session = null;
	boolean loggedIn = false;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void dologin() throws IOException, NoSuchAlgorithmException {

		String myHash = "";
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		md.update(passwd.getBytes());
		byte[] digest = md.digest();
		myHash = DatatypeConverter.printHexBinary(digest);

		user = userRemote.getconnecte(login, myHash);

		if (user != null) {
			session = Util.getSession();
			session.setAttribute("user.account", user);
			session.setAttribute("login.bean", this);
			session.setAttribute("username", user.getName());
			session.setAttribute("userid", user.getId());

			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/welcome.jsf");

		} else {
			addMessage(FacesMessage.SEVERITY_ERROR, "Info Message", " Incorrect Login or Lassword");
								
		}	
	}

	
				
	public void DoLogout()
			throws IOException, AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException,
			MBeanException, ReflectionException, InterruptedException, NoSuchAlgorithmException {
	
		HttpSession session = Util.getSession();
		session.invalidate();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/login.jsf");
	}

	public UserLogin getUser() {
		return user;
	}

	public void setUser(UserLogin user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
