package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.SexEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.SexService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SexController extends AbstractController<SexEntity> {

    private static final List<String> COLUMNS_NAMES = List.of("id", "sex");

    public SexController() {
        super(new SexService());
    }

    @Override public List<SexEntity> findAll() {
        List<SexEntity> entities = super.findAll();
        if (entities != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            for (SexEntity entity : entities) {
                body.add(entityToList(entity));
            }
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entities;
    }

    @Override public SexEntity findById(Integer id) {
        SexEntity entity = super.findById(id);
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

    @Override public SexEntity create(SexEntity entity) {
        super.enterValueForColumn(entity, SexEntity::setSex, "sex", String.class, false, 45);
        SexEntity createdEntity = super.create(entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(createdEntity));
        Formatter.formatTable(headerList, body);
        return createdEntity;
    }

    @Override public SexEntity update(Integer id, SexEntity entity) {
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
            super.enterValueForColumn(entity, SexEntity::setSex, "sex", String.class, false, 45);
        }
        SexEntity oldEntity = super.update(id, entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(oldEntity));
        body.add(entityToList(entity));
        Formatter.formatTable(headerList, body);
        return entity;
    }

    @Override public SexEntity delete(Integer id) {
        SexEntity entity = super.delete(id);
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

    @Override protected List<String> entityToList(SexEntity entity) {
        String id = entity.getId().toString();
        String sex = entity.getSex();
        return new LinkedList<>(List.of(id, sex));
    }
}
