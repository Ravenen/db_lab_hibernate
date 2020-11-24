package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.ServiceTypeEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.ServiceTypeService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ServiceTypeController extends AbstractController<ServiceTypeEntity> {

    private static final List<String> COLUMNS_NAMES = List.of("id", "type");

    public ServiceTypeController() {
        super(new ServiceTypeService());
    }

    @Override public List<ServiceTypeEntity> findAll() {
        List<ServiceTypeEntity> entities = super.findAll();
        if (entities != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            for (ServiceTypeEntity entity : entities) {
                body.add(entityToList(entity));
            }
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entities;
    }

    @Override public ServiceTypeEntity findById(Integer id) {
        ServiceTypeEntity entity = super.findById(id);
        if (entity != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            body.add(entityToList(entity));
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entity;
    }

    @Override public ServiceTypeEntity create(ServiceTypeEntity entity) {
        super.enterValueForColumn(entity, ServiceTypeEntity::setType, "type", String.class, false, 45);
        ServiceTypeEntity createdEntity = super.create(entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(createdEntity));
        Formatter.formatTable(headerList, body);
        return createdEntity;
    }

    @Override public ServiceTypeEntity update(Integer id, ServiceTypeEntity entity) {
        Scanner input = new Scanner(System.in, "UTF-8");
        String column;
        while (true) {
            System.out.println(ENTER_COLUMN_NAME);
            for (String columnName : COLUMNS_NAMES) {
                System.out.println("\t-" + columnName);
            }
            String inputText = input.nextLine();
            if (COLUMNS_NAMES.contains(inputText)) {
                column = inputText;
                break;
            } else {
                System.out.println(ERROR_INVALID_VALUE);
            }
        }
        if ("post".equals(column)) {
            super.enterValueForColumn(entity, ServiceTypeEntity::setType, "type", String.class, false, 45);
        }
        ServiceTypeEntity oldEntity = super.update(id, entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(oldEntity));
        body.add(entityToList(entity));
        Formatter.formatTable(headerList, body);
        return entity;
    }

    @Override public ServiceTypeEntity delete(Integer id) {
        ServiceTypeEntity entity = super.delete(id);
        if (entity != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            body.add(entityToList(entity));
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entity;
    }

    @Override protected List<String> entityToList(ServiceTypeEntity entity) {
        String id = entity.getId().toString();
        String type = entity.getType();
        return new LinkedList<>(List.of(id, type));
    }
}
