package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.AddressEntity;
import ua.lviv.iot.hiberlab.model.entity.CityEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.AddressService;
import ua.lviv.iot.hiberlab.model.service.implementation.CityService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AddressController extends AbstractController<AddressEntity> {

    private static final List<String> COLUMNS_NAMES = List.of("id", "street", "house", "city_id");

    public AddressController() {
        super(new AddressService());
    }

    @Override public List<AddressEntity> findAll() {
        List<AddressEntity> entities = super.findAll();
        if (entities != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            for (AddressEntity address : entities) {
                body.add(entityToList(address));
            }
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entities;
    }

    @Override public AddressEntity findById(Integer id) {
        AddressEntity address = super.findById(id);
        if (address != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            body.add(entityToList(address));
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return address;
    }

    @Override public AddressEntity create(AddressEntity entity) {
        super.enterValueForColumn(entity, AddressEntity::setStreet, "street", String.class, false, 64);
        super.enterValueForColumn(entity, AddressEntity::setHouse, "house", Integer.class, true, -1);
        Scanner input = new Scanner(System.in, "UTF-8");
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, "city_id", "", "");
            String inputText = input.nextLine();
            try {
                Integer value = Integer.parseInt(inputText);
                Service<CityEntity> cityService = new CityService();
                CityEntity city = cityService.findById(value);
                if (city != null) {
                    entity.setCityByCityId(city);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_INVALID_VALUE);
                System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
            }
        }
        AddressEntity createdEntity = super.create(entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(createdEntity));
        Formatter.formatTable(headerList, body);
        return createdEntity;
    }

    @Override public AddressEntity update(Integer id, AddressEntity entity) {
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
        switch (column) {
        case ("street"):
            super.enterValueForColumn(entity, AddressEntity::setStreet, "street", String.class, false, 64);
            break;
        case ("house"):
            super.enterValueForColumn(entity, AddressEntity::setHouse, "house", Integer.class, true, -1);
            break;
        case ("city_id"):
            while (true) {
                System.out.printf(ENTER_DATA_FORMAT, "city_id", "", "");
                String inputText = input.nextLine();
                try {
                    Integer value = Integer.parseInt(inputText);
                    Service<CityEntity> cityService = new CityService();
                    CityEntity city = cityService.findById(value);
                    if (city != null) {
                        entity.setCityByCityId(city);
                        break;
                    } else {
                        System.out.println(ERROR_INVALID_VALUE);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(ERROR_INVALID_VALUE);
                    System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                }
            }
            break;
        }
        AddressEntity oldEntity = super.update(id, entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(oldEntity));
        body.add(entityToList(entity));
        Formatter.formatTable(headerList, body);
        return entity;
    }

    @Override public AddressEntity delete(Integer id) {
        AddressEntity address = super.delete(id);
        if (address != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            body.add(entityToList(address));
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return address;
    }

    @Override protected List<String> entityToList(AddressEntity entity) {
        String id = entity.getId().toString();
        String street = entity.getStreet();
        String house = entity.getHouse() == null ? "-" : entity.getHouse().toString();
        String cityId = entity.getCityByCityId().getId().toString();
        return new LinkedList<>(List.of(id, street, house, cityId));
    }
}
