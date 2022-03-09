package com.example.demo.controller.components;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;


public class SideBar extends FormLayout {

    public TextField id  =  new TextField("Id");
    public Button idInput =  new Button("Put By Id");

    public TextField name = new  TextField("name");
    public TextField surname = new TextField("surname");
    public EmailField email = new EmailField("email");
    public TextField number = new TextField("number");

    public Button add = new Button("Add");
    public Button change = new Button("Change");
    public Button delete = new Button("Delete");

    public SideBar(){
        addClassName("info-form");

        HorizontalLayout idFindBar = new HorizontalLayout(id, idInput);
        idFindBar.addClassName("id-find-bar");
        idFindBar.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);

        HorizontalLayout buttonBar = new HorizontalLayout(add, change, delete);
        buttonBar.addClassName("button-bar");

        add(
                idFindBar,
                name,
                surname,
                email,
                number,
                buttonBar
        );
        setWidth("25em");
    }

}
