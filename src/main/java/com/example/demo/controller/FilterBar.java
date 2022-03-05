package com.example.demo.controller;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class FilterBar extends HorizontalLayout {

    public TextField filterText = new TextField();
    public Button addContactButton;

    public FilterBar(){
        addClassName("toolbar");

        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        addContactButton = new Button("Add contact");

        add(filterText, addContactButton);
    }
}
