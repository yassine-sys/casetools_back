package com.alert.entities;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import com.alerte.Mbean.LoginMbean;

 

public class Etl_Filtre implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
	
		LoginMbean authBean = (LoginMbean) req.getSession().getAttribute("login.bean");

		boolean letGo = false;
		
		if ((authBean != null) && (authBean.isLoggedIn())) {
			System.out.println("url=" + url);
			letGo = true;
		}

		if (letGo) {
			
			resp.setHeader("Access-Control-Allow-Origin", "*"); // Replace with your Angular application's URL
			resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type");
			chain.doFilter(request, response);
		} else {
			System.out.println("letGo false empty loginbean ");
			resp.sendRedirect(req.getContextPath() + "/login.jsf");
		}

	}

	 

	   


	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
