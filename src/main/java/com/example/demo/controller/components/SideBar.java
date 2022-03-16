package com.example.demo.controller.components;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;


public class SideBar extends FormLayout {

    public TextField id  =  new TextField("Id");
    public Button idInput =  new Button("Put By Id");

    public TextField name = new  TextField("name");
    public TextField surname = new TextField("surname");
    public TextField number = new TextField("number");
    public NumberField salary = new NumberField("salary");
    public TextField hiringDate = new TextField("date d-m-y");

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
                number,
                salary,
                hiringDate,
                buttonBar
        );
        setWidth("25em");
    }

}
