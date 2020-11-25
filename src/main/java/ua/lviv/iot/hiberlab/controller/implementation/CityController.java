package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.CityEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.CityService;
import ua.lviv.iot.hiberlab.model.service.implementation.CountryService;
import ua.lviv.iot.hiberlab.model.service.implementation.RegionService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CityController extends AbstractController<CityEntity> {

    private static final List<String> COLUMNS_NAMES = List.of("id", "name", "postal_code", "country", "region");

    public CityController() {
        super(new CityService());
    }

    @Override
    public List<CityEntity> findAll() {
        List<CityEntity> entities = super.findAll();
        if (entities != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            for (CityEntity entity : entities) {
                body.add(entityToList(entity));
            }
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entities;
    }

    @Override
    public CityEntity findById(Integer id) {
        CityEntity entity = super.findById(id);
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

    @Override
    public CityEntity create(CityEntity entity) {
        super.enterValueForColumn(entity, CityEntity::setName, "name", String.class, false, 45);
        super.enterValueForColumn(entity, CityEntity::setPostalCode, "postal_code", String.class, true, 6);
        super.enterEntityValueForColumn(entity, CityEntity::setCountryByCountryId, "country_id", new CountryService(), false);
        super.enterEntityValueForColumn(entity, CityEntity::setRegionByRegionId, "region_id", new RegionService(), true);
        CityEntity createdEntity = super.create(entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(createdEntity));
        Formatter.formatTable(headerList, body);
        return createdEntity;
    }

    @Override
    public CityEntity update(Integer id, CityEntity entity) {
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
            case ("name"):
                super.enterValueForColumn(entity, CityEntity::setName, "name", String.class, false, 45);
                break;
            case ("postal_code"):
                super.enterValueForColumn(entity, CityEntity::setPostalCode, "postal_code", String.class, true, 6);
                break;
            case ("country"):
                super.enterEntityValueForColumn(entity, CityEntity::setCountryByCountryId, "country_id", new CountryService(), false);
                break;
            case ("region"):
                super.enterEntityValueForColumn(entity, CityEntity::setRegionByRegionId, "region_id", new RegionService(), true);
                break;
        }
        CityEntity oldEntity = super.update(id, entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(oldEntity));
        body.add(entityToList(entity));
        Formatter.formatTable(headerList, body);
        return entity;
    }

    @Override
    public CityEntity delete(Integer id) {
        CityEntity entity = super.delete(id);
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

    @Override
    protected List<String> entityToList(CityEntity entity) {
        String id = entity.getId().toString();
        String name = entity.getName();
        String postalCode = entity.getPostalCode() == null ? "-" : entity.getPostalCode();
        String countryName = entity.getCountryByCountryId().getName();
        String regionName = entity.getRegionByRegionId() == null ? "-" : entity.getRegionByRegionId().getName();
        return new LinkedList<>(List.of(id, name, postalCode, countryName, regionName));
    }
}
