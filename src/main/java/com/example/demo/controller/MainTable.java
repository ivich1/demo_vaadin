package com.example.demo.controller;

import com.example.demo.Entity.SimpleEntity;
import com.example.demo.controller.components.FilterBar;
import com.example.demo.controller.components.SideBar;
import com.example.demo.controller.components.SimpleGrid;
import com.example.demo.service.SimpleService;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
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
        sideBar.add.addClickListener(event -> save());
        sideBar.idInput.addClickListener(event -> getEntityToBar());
        sideBar.delete.addClickListener(event ->delete() );
        sideBar.change.addClickListener(event -> change());

        HorizontalLayout tmpLayout = new HorizontalLayout(grid, sideBar);
        tmpLayout.addClassName("tmp-layout");
        tmpLayout.setSizeFull();

        add(
                filterBar,
                tmpLayout
        );
        updateList();

    }

    //не знаю, как отделить привязку функионала от отображения, оставлю тут
    private void updateList() {
        grid.setItems(service.findAll(filterBar.filterText.getValue()));
    }

        private SimpleEntity takeInfo(){
        String name = sideBar.name.getValue();
        String surname = sideBar.surname.getValue();
        String email = sideBar.email.getValue();
        int number = Integer.parseInt(sideBar.number.getValue());
        return new SimpleEntity(name,surname,email,number);
    }

    private void change(){
        SimpleEntity tmp = takeInfo();
        tmp.setId(Long.getLong(sideBar.id.getValue()));
        service.change(sideBar.id.getValue() ,tmp);
    }
    private void delete(){
        Long tmp = Long.parseLong(sideBar.id.getValue());
        service.delete(tmp);
    }

    private void save(){
        //SimpleEntity tmp = takeInfo();
        String name = sideBar.name.getValue();
        String surname = sideBar.surname.getValue();
        String email = sideBar.email.getValue();
        int number = Integer.parseInt(sideBar.number.getValue());
        SimpleEntity tmp = new SimpleEntity(name,surname,email,number);
        service.save(tmp);
    }

    private void takeInfoToSave(){
        String name = sideBar.name.getValue();
        String surname = sideBar.surname.getValue();
        String email = sideBar.email.getValue();
        int number = Integer.parseInt(sideBar.number.getValue());
        SimpleEntity tmp = new SimpleEntity(name,surname,email,number);
        service.save(tmp);
    }

    private void getEntityToBar(){
        Long id =  Long.valueOf(sideBar.id.getValue());
        SimpleEntity simpleEntity = service.fillById(id);

        sideBar.name.setValue(simpleEntity.getName());
        sideBar.surname.setValue(simpleEntity.getSurname());
        sideBar.email.setValue(simpleEntity.getEmail());
        sideBar.number.setValue(Integer.toString(simpleEntity.getNumber()));
    }

}
