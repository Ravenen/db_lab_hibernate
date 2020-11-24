package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.CountryEntity;
import ua.lviv.iot.hiberlab.model.entity.RegionEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.CountryService;
import ua.lviv.iot.hiberlab.model.service.implementation.RegionService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RegionController extends AbstractController<RegionEntity> {

  private static final List<String> COLUMNS_NAMES = List.of("id", "name", "country_id");

  public RegionController() {
    super(new RegionService());
  }

  @Override
  public List<RegionEntity> findAll() {
    List<RegionEntity> entities = super.findAll();
    if (entities != null) {
      List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
      List<List<String>> body = new LinkedList<>();
      for (RegionEntity entity : entities) {
        body.add(entityToList(entity));
      }
      Formatter.formatTable(headerList, body);
    } else {
      System.out.println(ERROR_NO_MATCHES_FOUND);
    }
    return entities;
  }

  @Override
  public RegionEntity findById(Integer id) {
    RegionEntity entity = super.findById(id);
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
  public RegionEntity create(RegionEntity entity) {
    super.enterValueForColumn(entity, RegionEntity::setName, "name", String.class, false, 45);
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
    RegionEntity createdEntity = super.create(entity);
    List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
    List<List<String>> body = new LinkedList<>();
    body.add(entityToList(createdEntity));
    Formatter.formatTable(headerList, body);
    return createdEntity;
  }

  @Override
  public RegionEntity update(Integer id, RegionEntity entity) {
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
        super.enterValueForColumn(entity, RegionEntity::setName, "name", String.class, false, 45);
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
    }
    RegionEntity oldEntity = super.update(id, entity);
    List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
    List<List<String>> body = new LinkedList<>();
    body.add(entityToList(oldEntity));
    body.add(entityToList(entity));
    Formatter.formatTable(headerList, body);
    return entity;
  }

  @Override
  public RegionEntity delete(Integer id) {
    RegionEntity entity = super.delete(id);
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
  protected List<String> entityToList(RegionEntity entity) {
    String id = entity.getId().toString();
    String name = entity.getName();
    String countryId = entity.getCountryByCountryId().getId().toString();
    return new LinkedList<>(List.of(id, name, countryId));
  }
}
