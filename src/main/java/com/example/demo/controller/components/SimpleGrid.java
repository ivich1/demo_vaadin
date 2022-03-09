package com.example.demo.controller.components;

import com.vaadin.flow.component.grid.Grid;

public class SimpleGrid<T> extends Grid<T> {

    public SimpleGrid(Class<T> beanType){
        super(beanType);
        addClassName("simple-grid");
        setSizeFull();
        setColumns("id", "name", "surname", "email", "number");
        getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
