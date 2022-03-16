package com.example.demo.controller;

import com.example.demo.entity.SimpleEntity;
import com.example.demo.controller.components.FilterBar;
import com.example.demo.controller.components.SideBar;
import com.example.demo.controller.components.SimpleGrid;
import com.example.demo.service.SecurityService;
import com.example.demo.service.SimpleService;
import com.example.demo.view.SimpleView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.Calendar;
import java.util.GregorianCalendar;

@PermitAll
@Route(value = "")
@PageTitle("Table")
public class MainTable extends VerticalLayout {

    SimpleService service;
    SecurityService securityService;

    FilterBar filterBar = new FilterBar();
    SimpleGrid<SimpleView> grid = new SimpleGrid(SimpleView.class);
    SideBar sideBar = new SideBar();
    Button loginButton;
    Button fillButton;

    MainTable(SimpleService service){
        this.service = service;
        addClassName("main-table");
        setSizeFull();
        securityService = new SecurityService();

        loginButton = new Button("log out",e -> securityService.logout());
        fillButton = new Button("add 3 entities", e -> fillTable());//просто кнопка ,чтобы заполнить таблицу
        HorizontalLayout header = new HorizontalLayout(loginButton, fillButton);
        header.addClassName("header");


        filterBar.filterText.addValueChangeListener(event -> updateList());
        sideBar.add.addClickListener(event -> save());
        sideBar.idInput.addClickListener(event -> getEntityToBar());
        sideBar.delete.addClickListener(event -> delete() );
        sideBar.change.addClickListener(event -> change());

        HorizontalLayout tmpLayout = new HorizontalLayout(grid, sideBar);
        grid.asSingleSelect().addValueChangeListener( e -> getEntityToBar(e.getValue()));
        tmpLayout.addClassName("tmp-layout");
        tmpLayout.setSizeFull();

        add(
                header,
                filterBar,
                tmpLayout
        );
        updateList();

    }

    //не знаю, как привильно, оставлю тут
    private void updateList() {
        grid.setItems(service.findAll(filterBar.filterText.getValue()));
    }

    private SimpleEntity takeInfo(){
        String name = sideBar.name.getValue();
        String surname = sideBar.surname.getValue();
        int number = Integer.parseInt(sideBar.number.getValue());
        double salary = sideBar.salary.getValue();
        String stringDate = sideBar.hiringDate.getValue();

        //с датой совсем все плохо
        Calendar date = stringToCalendar(stringDate);

        return new SimpleEntity(name, surname, number, salary, date);
    }

    private void change(){
        SimpleEntity tmp = takeInfo();
        Long id = Long.parseLong(sideBar.id.getValue());
        service.change(id, tmp);
        updateList();
    }

    private void delete(){
        Long tmp = Long.parseLong(sideBar.id.getValue());
        service.delete(tmp);
        updateList();

    }

    private void save(){
        SimpleEntity tmp = takeInfo();
        service.save(tmp);
        updateList();
    }

    private void getEntityToBar(){
        Long id =  Long.valueOf(sideBar.id.getValue());
        SimpleEntity simpleEntity = service.fillById(id);

        sideBar.name.setValue(simpleEntity.getName());
        sideBar.surname.setValue(simpleEntity.getSurname());
        sideBar.number.setValue(Integer.toString(simpleEntity.getNumber()));
        sideBar.salary.setValue(simpleEntity.getSalary());
        sideBar.hiringDate.setValue(calendarToString(simpleEntity.getHiringDate()));
    }
    private void getEntityToBar(SimpleView simpleEntity){
        sideBar.id.setValue((simpleEntity.getId()).toString());
        sideBar.name.setValue(simpleEntity.getName());
        sideBar.surname.setValue(simpleEntity.getSurname());
        sideBar.number.setValue(Integer.toString(simpleEntity.getNumber()));
        sideBar.salary.setValue(simpleEntity.getSalary());
        sideBar.hiringDate.setValue(simpleEntity.getHiringDate());

    }

    private void fillTable(){
        service.fillTable();
        updateList();
    }

    private void selectEntity( SimpleEntity entity){

    }

    private String calendarToString(Calendar calendar){
        var d = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        var m = Integer.toString(calendar.get(Calendar.MONTH));
        var y = Integer.toString(calendar.get(Calendar.YEAR));

        return d + "-" + m + "-" + y;
    }

    private Calendar stringToCalendar(String stringDate){
        int[] tmp = new int[3];
        int i = 0;
        for(String unit : stringDate.split("-", 3)){
            tmp[i] = Integer.parseInt(unit);
            i++;
        }
        return new GregorianCalendar(tmp[2],tmp[1],tmp[1]);
    }

}
