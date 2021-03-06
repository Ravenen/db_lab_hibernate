package ua.lviv.iot.hiberlab.controller.implementation;

import java.util.LinkedList;
import java.util.List;
import ua.lviv.iot.hiberlab.model.entity.ManufacturerEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.CountryService;
import ua.lviv.iot.hiberlab.model.service.implementation.ManufacturerService;
import ua.lviv.iot.hiberlab.view.Formatter;

public class ManufacturerController extends AbstractController<ManufacturerEntity> {

  private static final List<String> COLUMNS_NAMES =
      List.of("id", "name", "contact_number", "country");

  public ManufacturerController() {
    super(new ManufacturerService());
  }

  @Override
  public List<ManufacturerEntity> findAll() {
    List<ManufacturerEntity> entities = super.findAll();
    if (entities != null) {
      List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
      List<List<String>> body = new LinkedList<>();
      for (ManufacturerEntity entity : entities) {
        body.add(entityToList(entity));
      }
      Formatter.formatTable(headerList, body);
    } else {
      System.out.println(ERROR_NO_MATCHES_FOUND);
    }
    return entities;
  }

  @Override
  public ManufacturerEntity findById(Integer id) {
    ManufacturerEntity entity = super.findById(id);
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
  public ManufacturerEntity create(ManufacturerEntity entity) {
    super.enterValueForColumn(entity, ManufacturerEntity::setName, "name", String.class, false, 45);
    super.enterValueForColumn(entity, ManufacturerEntity::setContactNumber, "contact_number",
        String.class, true,
        13);
    super.enterEntityValueForColumn(entity, ManufacturerEntity::setCountryByCountryId, "country_id",
        new CountryService(), false);
    ManufacturerEntity createdEntity = super.create(entity);
    List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
    List<List<String>> body = new LinkedList<>();
    body.add(entityToList(createdEntity));
    Formatter.formatTable(headerList, body);
    return createdEntity;
  }

  @Override
  public ManufacturerEntity update(Integer id, ManufacturerEntity entity) {
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
      case "name":
        super.enterValueForColumn(entity, ManufacturerEntity::setName, "name", String.class, false,
            45);
        break;
      case "contact_number":
        super.enterValueForColumn(entity, ManufacturerEntity::setContactNumber, "contact_number",
            String.class, true,
            13);
        break;
      case "country":
        super.enterEntityValueForColumn(entity, ManufacturerEntity::setCountryByCountryId,
            "country_id", new CountryService(), false);
        break;
      default:
        System.out.println(ERROR_NO_SUCH_OPTION);
    }
    ManufacturerEntity oldEntity = super.update(id, entity);
    List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
    List<List<String>> body = new LinkedList<>();
    body.add(entityToList(oldEntity));
    body.add(entityToList(entity));
    Formatter.formatTable(headerList, body);
    return entity;
  }

  @Override
  public ManufacturerEntity delete(Integer id) {
    ManufacturerEntity entity = super.delete(id);
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
  protected List<String> entityToList(ManufacturerEntity entity) {
    String id = entity.getId().toString();
    String name = entity.getName();
    String contactNumber = entity.getContactNumber() == null ? "-" : entity.getContactNumber();
    String countryName = entity.getCountryByCountryId().getName();
    return new LinkedList<>(List.of(id, name, contactNumber, countryName));
  }
}
