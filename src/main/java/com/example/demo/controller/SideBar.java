package com.example.demo.controller;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;


public class SideBar extends FormLayout {

    public TextField name = new  TextField("name");
    public TextField surname = new TextField("surname");
    public EmailField email = new EmailField("email");
    public NumberField number = new NumberField("number");

    public Button save = new Button("Save");
    public Button delete = new Button("delete");
    public Button close = new Button("Close");

    public SideBar(){
        addClassName("info-form");

        HorizontalLayout buttonBar = new HorizontalLayout(save, delete, close);
        buttonBar.addClassName("button-bar");

        add(
                name,
                surname,
                email,
                number,
                buttonBar
        );
        setWidth("25em");
    }
}
