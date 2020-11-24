package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.CityEntity;
import ua.lviv.iot.hiberlab.model.entity.CountryEntity;
import ua.lviv.iot.hiberlab.model.entity.RegionEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.CityService;
import ua.lviv.iot.hiberlab.model.service.implementation.CountryService;
import ua.lviv.iot.hiberlab.model.service.implementation.RegionService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CityController extends AbstractController<CityEntity> {

    private static final List<String> COLUMNS_NAMES = List.of("id", "name", "postal_code", "country_id", "region_id");

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
        Scanner input = new Scanner(System.in, "UTF-8");
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, "country_id", "", "");
            String inputText = input.nextLine();
            try {
                Integer value = Integer.parseInt(inputText);
                Service<CountryEntity> countryService = new CountryService();
                CountryEntity country = countryService.findById(value);
                if (country != null) {
                    entity.setCountryByCountryId(country);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_INVALID_VALUE);
                System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
            }
        }
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, "region_id", "", "(optional)");
            String inputText = input.nextLine();
            try {
                Integer value = Integer.parseInt(inputText);
                Service<RegionEntity> regionService = new RegionService();
                RegionEntity region = regionService.findById(value);
                if (region != null) {
                    entity.setRegionByRegionId(region);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                }
            } catch (IllegalArgumentException e) {
                if (!inputText.equals("")) {
                    System.out.println(ERROR_INVALID_VALUE);
                    System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                } else {
                    break;
                }
            }
        }
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
            case ("country_id"):
                while (true) {
                    System.out.printf(ENTER_DATA_FORMAT, "country_id", "", "");
                    String inputText = input.nextLine();
                    try {
                        Integer value = Integer.parseInt(inputText);
                        Service<CountryEntity> countryService = new CountryService();
                        CountryEntity country = countryService.findById(value);
                        if (country != null) {
                            entity.setCountryByCountryId(country);
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
            case ("region_id"):
                while (true) {
                    System.out.printf(ENTER_DATA_FORMAT, "region_id", "", "(optional)");
                    String inputText = input.nextLine();
                    try {
                        Integer value = Integer.parseInt(inputText);
                        Service<RegionEntity> regionService = new RegionService();
                        RegionEntity region = regionService.findById(value);
                        if (region != null) {
                            entity.setRegionByRegionId(region);
                            break;
                        } else {
                            System.out.println(ERROR_INVALID_VALUE);
                        }
                    } catch (IllegalArgumentException e) {
                        if (!inputText.equals("")) {
                            System.out.println(ERROR_INVALID_VALUE);
                            System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                        } else {
                            entity.setRegionByRegionId(null);
                            break;
                        }
                    }
                }
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
        String countryId = entity.getCountryByCountryId().getId().toString();
        String regionId = entity.getRegionByRegionId() == null ? "-" : entity.getRegionByRegionId().getId().toString();
        return new LinkedList<>(List.of(id, name, postalCode, countryId, regionId));
    }
}
