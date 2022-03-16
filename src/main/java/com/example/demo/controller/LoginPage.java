package com.example.demo.controller;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route("login")
@PageTitle("login page")
public class LoginPage extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginPage(){
        addClassName("login-page");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setAction("login");

        H1 tittle = new H1("Vaadin Login Page");
        H1 info = new H1("user/ password");

        add(tittle, info, login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
