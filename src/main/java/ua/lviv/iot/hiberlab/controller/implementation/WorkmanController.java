package ua.lviv.iot.hiberlab.controller.implementation;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import ua.lviv.iot.hiberlab.model.entity.WorkmanEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.PostService;
import ua.lviv.iot.hiberlab.model.service.implementation.SexService;
import ua.lviv.iot.hiberlab.model.service.implementation.WorkmanService;
import ua.lviv.iot.hiberlab.view.Formatter;

public class WorkmanController extends AbstractController<WorkmanEntity> {

  private static final List<String> COLUMNS_NAMES =
      List.of("id", "name", "surname", "price_per_hour_uah", "contact_number", "sex", "post");

  public WorkmanController() {
    super(new WorkmanService());
  }

  @Override
  public List<WorkmanEntity> findAll() {
    List<WorkmanEntity> entities = super.findAll();
    if (entities != null) {
      List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
      List<List<String>> body = new LinkedList<>();
      for (WorkmanEntity entity : entities) {
        body.add(entityToList(entity));
      }
      Formatter.formatTable(headerList, body);
    } else {
      System.out.println(ERROR_NO_MATCHES_FOUND);
    }
    return entities;
  }

  @Override
  public WorkmanEntity findById(Integer id) {
    WorkmanEntity entity = super.findById(id);
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
  public WorkmanEntity create(WorkmanEntity entity) {
    super.enterValueForColumn(entity, WorkmanEntity::setName, "name", String.class, false, 45);
    super
        .enterValueForColumn(entity, WorkmanEntity::setSurname, "surname", String.class, false, 45);
    super.enterValueForColumn(entity, WorkmanEntity::setPricePerHourUah, "price_per_hour_uah",
        BigDecimal.class, false, -1);
    super.enterValueForColumn(entity, WorkmanEntity::setContactNumber, "contact_number",
        String.class, true, 13);
    super
        .enterEntityValueForColumn(entity, WorkmanEntity::setSexBySexId, "sex_id", new SexService(),
            true);
    super.enterEntityValueForColumn(entity, WorkmanEntity::setPostByPostId, "post_id",
        new PostService(), false);
    WorkmanEntity createdEntity = super.create(entity);
    List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
    List<List<String>> body = new LinkedList<>();
    body.add(entityToList(createdEntity));
    Formatter.formatTable(headerList, body);
    return createdEntity;
  }

  @Override
  public WorkmanEntity update(Integer id, WorkmanEntity entity) {
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
        super.enterValueForColumn(entity, WorkmanEntity::setName, "name", String.class, false, 45);
        break;
      case "surname":
        super.enterValueForColumn(entity, WorkmanEntity::setSurname, "surname", String.class, false,
            45);
        break;
      case "price_per_hur_uah":
        super.enterValueForColumn(entity, WorkmanEntity::setPricePerHourUah, "price_per_hour_uah",
            BigDecimal.class, false, -1);
        break;
      case "contact_number":
        super.enterValueForColumn(entity, WorkmanEntity::setContactNumber, "contact_number",
            String.class, true, 13);
        break;
      case "sex":
        super.enterEntityValueForColumn(entity, WorkmanEntity::setSexBySexId, "sex_id",
            new SexService(), true);
        break;
      case "post":
        super.enterEntityValueForColumn(entity, WorkmanEntity::setPostByPostId, "post_id",
            new PostService(), false);
        break;
      default:
        System.out.println(ERROR_NO_SUCH_OPTION);
    }
    WorkmanEntity oldEntity = super.update(id, entity);
    List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
    List<List<String>> body = new LinkedList<>();
    body.add(entityToList(oldEntity));
    body.add(entityToList(entity));
    Formatter.formatTable(headerList, body);
    return entity;
  }

  @Override
  public WorkmanEntity delete(Integer id) {
    WorkmanEntity entity = super.delete(id);
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
  protected List<String> entityToList(WorkmanEntity entity) {
    String id = entity.getId().toString();
    String name = entity.getName();
    String surname = entity.getSurname();
    String pricePerHourUah = entity.getPricePerHourUah().toString();
    String contactNumber = entity.getContactNumber() == null ? "-" : entity.getContactNumber();
    String sexName = entity.getSexBySexId() == null ? "-" : entity.getSexBySexId().getSex();
    String postName = entity.getPostByPostId().getPost();
    return new LinkedList<>(
        List.of(id, name, surname, pricePerHourUah, contactNumber, sexName, postName));
  }
}
