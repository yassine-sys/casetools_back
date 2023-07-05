package com.alert.entities;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alerte.Mbean.LoginMbean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class Util {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static UserLogin getUser() {
		HttpSession session = getSession();
		if (session != null)
			return (UserLogin) session.getAttribute("user.account");
		else
			return null;
	}

	public static LoginMbean lg() {
		HttpSession session = getSession();
		if (session != null)
			return (LoginMbean) session.getAttribute("login.bean");
		else
			return null;
	}

}
