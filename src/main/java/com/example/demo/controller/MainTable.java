package com.example.demo.controller;


import com.example.demo.Entity.SimpleEntity;
import com.example.demo.service.SimpleService;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("Table")
public class MainTable extends VerticalLayout {

    FilterBar filterBar = new FilterBar();
    SimpleGrid<SimpleEntity> grid = new SimpleGrid<SimpleEntity>(SimpleEntity.class);
    SideBar sideBar = new SideBar();
    SimpleService service;

    MainTable(SimpleService service){
        this.service = service;
        addClassName("main-table");
        setSizeFull();

        filterBar.filterText.addValueChangeListener(event -> updateList());
        sideBar.save.addClickListener(event -> takeInfoToSave());

        HorizontalLayout tmpLayout = new HorizontalLayout(grid, sideBar);
        tmpLayout.addClassName("tmp-layout");
        tmpLayout.setSizeFull();

        add(
                filterBar,
                tmpLayout
        );
        updateList();

    }

    //не знаю, как реализовать, оставлю тут
    private void updateList() {
        grid.setItems(service.findAll(filterBar.filterText.getValue()));
    }

    private void takeInfoToSave(){
        String name = sideBar.name.getValue();
        String surname = sideBar.surname.getValue();
        String email = sideBar.email.getValue();
        int number = sideBar.number.getValue().intValue();
        SimpleEntity tmp = new SimpleEntity(name,surname,email,number);
        service.save(tmp);
    }

}
