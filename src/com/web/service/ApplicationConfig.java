package com.web.service;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.alert.entities.UserLogin;
import com.alerte.Mbean.AlerteBean;
import com.alerte.Mbean.LoginMbean;
import com.alerte.Mbean.UserMbean;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        //add all resources classes
        resources.add(AlerteBean.class);
        resources.add(UserMbean.class);
        resources.add(LoginMbean.class);
    }
}