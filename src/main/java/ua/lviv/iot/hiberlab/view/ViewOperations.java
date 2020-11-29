package ua.lviv.iot.hiberlab.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import ua.lviv.iot.hiberlab.controller.Controller;

public class ViewOperations<T> {

  private static final String ERROR_INVALID_VALUE = "[Error] Entered invalid value";
  private static final String ERROR_MESSAGE_FORMAT = "[Error] Message: %s %n";

  private static final String KEY_EXIT = "Q";
  private static final String TEXT_ENTER_FIELD_OR_QUIT_FORMAT =
      "Enter %s or press '" + KEY_EXIT + "' to go back: %n";

  private static final Scanner input = new Scanner(System.in, "UTF-8");

  private final Controller<T> controller;
  private final Class<T> entityClass;

  public ViewOperations(Controller<T> controller, Class<T> entityClass) {
    this.controller = controller;
    this.entityClass = entityClass;
  }

  public void findAll() {
    controller.findAll();
  }

  public void findById() {
    String keyMenu;
    do {
      System.out.printf(TEXT_ENTER_FIELD_OR_QUIT_FORMAT, "id");
      keyMenu = input.nextLine().toUpperCase();
      if (!keyMenu.equals(KEY_EXIT)) {
        try {
          Integer id = Integer.parseInt(keyMenu);
          T foundEntity = controller.findById(id);
          if (foundEntity != null) {
            return;
          }
        } catch (NumberFormatException e) {
          System.out.println(ERROR_INVALID_VALUE);
          System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
        }
      }
    } while (!keyMenu.equals(KEY_EXIT));
  }

  public void create() {
    try {
      T newEntity = entityClass.getConstructor().newInstance();
      controller.create(newEntity);
    } catch (InstantiationException | IllegalAccessException
        | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    String keyMenu;
    do {
      System.out.printf(TEXT_ENTER_FIELD_OR_QUIT_FORMAT, "id");
      keyMenu = input.nextLine().toUpperCase();
      if (!keyMenu.equals(KEY_EXIT)) {
        try {
          Integer id = Integer.parseInt(keyMenu);
          T foundEntity = controller.findById(id);
          if (foundEntity != null) {
            controller.update(id, foundEntity);
            break;
          }
        } catch (NumberFormatException e) {
          System.out.println(ERROR_INVALID_VALUE);
          System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
        }
      }
    } while (!keyMenu.equals(KEY_EXIT));
  }

  public void delete() {
    String keyMenu;
    do {
      System.out.printf(TEXT_ENTER_FIELD_OR_QUIT_FORMAT, "id");
      keyMenu = input.nextLine().toUpperCase();
      if (!keyMenu.equals(KEY_EXIT)) {
        try {
          Integer id = Integer.parseInt(keyMenu);
          T deletedEntity = controller.delete(id);
          if (deletedEntity != null) {
            return;
          }
        } catch (NumberFormatException e) {
          System.out.println(ERROR_INVALID_VALUE);
          System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
        }
      }

    } while (!keyMenu.equals(KEY_EXIT));
  }

}
