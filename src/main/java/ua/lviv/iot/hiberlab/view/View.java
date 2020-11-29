package ua.lviv.iot.hiberlab.view;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import ua.lviv.iot.hiberlab.controller.Controller;
import ua.lviv.iot.hiberlab.controller.implementation.AddressController;
import ua.lviv.iot.hiberlab.controller.implementation.CityController;
import ua.lviv.iot.hiberlab.controller.implementation.CountryController;
import ua.lviv.iot.hiberlab.controller.implementation.ManufacturerController;
import ua.lviv.iot.hiberlab.controller.implementation.PostController;
import ua.lviv.iot.hiberlab.controller.implementation.RegionController;
import ua.lviv.iot.hiberlab.controller.implementation.ServiceController;
import ua.lviv.iot.hiberlab.controller.implementation.ServiceTypeController;
import ua.lviv.iot.hiberlab.controller.implementation.SexController;
import ua.lviv.iot.hiberlab.controller.implementation.TerminalController;
import ua.lviv.iot.hiberlab.controller.implementation.TerminalTypeController;
import ua.lviv.iot.hiberlab.controller.implementation.WorkmanController;
import ua.lviv.iot.hiberlab.model.entity.AddressEntity;
import ua.lviv.iot.hiberlab.model.entity.CityEntity;
import ua.lviv.iot.hiberlab.model.entity.CountryEntity;
import ua.lviv.iot.hiberlab.model.entity.ManufacturerEntity;
import ua.lviv.iot.hiberlab.model.entity.PostEntity;
import ua.lviv.iot.hiberlab.model.entity.RegionEntity;
import ua.lviv.iot.hiberlab.model.entity.ServiceEntity;
import ua.lviv.iot.hiberlab.model.entity.ServiceTypeEntity;
import ua.lviv.iot.hiberlab.model.entity.SexEntity;
import ua.lviv.iot.hiberlab.model.entity.TerminalEntity;
import ua.lviv.iot.hiberlab.model.entity.TerminalTypeEntity;
import ua.lviv.iot.hiberlab.model.entity.WorkmanEntity;

public class View {

  private static final String KEY_EXIT = "Q";

  private static final String ERROR_NO_SUCH_OPTION = "No such option found. Try again.";

  private static final String TEXT_SELECT_MENU_OPTION = "Please choose menu option: ";
  private static final String TEXT_GO_BACK = "Go back or quit";

  private static final Scanner input = new Scanner(System.in, "UTF-8");

  public View() {
  }

  public void show() {
    showTablesMenu();
  }

  private void showTablesMenu() {
    Map<String, String> tablesMenu = generateTablesMenu();
    Map<String, Printable> tablesMenuMethods = generateTablesMenuMethods();
    showMenuFromMaps(tablesMenu, tablesMenuMethods);
  }

  private Map<String, String> generateTablesMenu() {
    Map<String, String> tablesMenu = new LinkedHashMap<>();
    tablesMenu.put("1", "Table: Country");
    tablesMenu.put("2", "Table: Region");
    tablesMenu.put("3", "Table: City");
    tablesMenu.put("4", "Table: Address");
    tablesMenu.put("5", "Table: Manufacturer");
    tablesMenu.put("6", "Table: Terminal type");
    tablesMenu.put("7", "Table: Terminal");
    tablesMenu.put("8", "Table: Sex");
    tablesMenu.put("9", "Table: Position");
    tablesMenu.put("10", "Table: Workman");
    tablesMenu.put("11", "Table: Service type");
    tablesMenu.put("12", "Table: Service");
    return tablesMenu;
  }

  private Map<String, Printable> generateTablesMenuMethods() {
    Map<String, Printable> tableMenuMethods = new LinkedHashMap<>();
    tableMenuMethods.put("1", this::showCountryMenu);
    tableMenuMethods.put("2", this::showRegionMenu);
    tableMenuMethods.put("3", this::showCityMenu);
    tableMenuMethods.put("4", this::showAddressMenu);
    tableMenuMethods.put("5", this::showManufacturerMenu);
    tableMenuMethods.put("6", this::showTerminalTypeMenu);
    tableMenuMethods.put("7", this::showTerminalMenu);
    tableMenuMethods.put("8", this::showSexMenu);
    tableMenuMethods.put("9", this::showPositionMenu);
    tableMenuMethods.put("10", this::showWorkmanMenu);
    tableMenuMethods.put("11", this::showServiceTypeMenu);
    tableMenuMethods.put("12", this::showServiceMenu);
    return tableMenuMethods;
  }

  private void showCountryMenu() {
    Map<String, String> menu = generateCountryMenu();
    Map<String, Printable> menuMethods = generateCountryMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showRegionMenu() {
    Map<String, String> menu = generateRegionMenu();
    Map<String, Printable> menuMethods = generateRegionMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showCityMenu() {
    Map<String, String> menu = generateCityMenu();
    Map<String, Printable> menuMethods = generateCityMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showAddressMenu() {
    Map<String, String> menu = generateAddressMenu();
    Map<String, Printable> menuMethods = generateAddressMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showManufacturerMenu() {
    Map<String, String> menu = generateManufacturerMenu();
    Map<String, Printable> menuMethods = generateManufacturerMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showTerminalTypeMenu() {
    Map<String, String> menu = generateTerminalTypeMenu();
    Map<String, Printable> menuMethods = generateTerminalTypeMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showTerminalMenu() {
    Map<String, String> menu = generateTerminalMenu();
    Map<String, Printable> menuMethods = generateTerminalMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showSexMenu() {
    Map<String, String> menu = generateSexMenu();
    Map<String, Printable> menuMethods = generateSexMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showPositionMenu() {
    Map<String, String> menu = generatePositionMenu();
    Map<String, Printable> menuMethods = generatePositionMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showWorkmanMenu() {
    Map<String, String> menu = generateWorkmanMenu();
    Map<String, Printable> menuMethods = generateWorkmanMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showServiceTypeMenu() {
    Map<String, String> menu = generateServiceTypeMenu();
    Map<String, Printable> menuMethods = generateServiceTypeMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private void showServiceMenu() {
    Map<String, String> menu = generateServiceMenu();
    Map<String, Printable> menuMethods = generateServiceMenuMethods();
    showMenuFromMaps(menu, menuMethods);
  }

  private Map<String, String> generateCountryMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all countries");
    menu.put("2", "Select country");
    menu.put("3", "Create country");
    menu.put("4", "Update country");
    menu.put("5", "Delete country");
    return menu;
  }

  private Map<String, Printable> generateCountryMenuMethods() {
    Controller<CountryEntity> countryController = new CountryController();
    ViewOperations<CountryEntity> countryOperation =
        new ViewOperations<>(countryController, CountryEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", countryOperation::findAll);
    menuMethods.put("2", countryOperation::findById);
    menuMethods.put("3", countryOperation::create);
    menuMethods.put("4", countryOperation::update);
    menuMethods.put("5", countryOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateRegionMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all regions");
    menu.put("2", "Select region");
    menu.put("3", "Create region");
    menu.put("4", "Update region");
    menu.put("5", "Delete region");
    return menu;
  }

  private Map<String, Printable> generateRegionMenuMethods() {
    Controller<RegionEntity> regionController = new RegionController();
    ViewOperations<RegionEntity> regionOperation =
        new ViewOperations<>(regionController, RegionEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", regionOperation::findAll);
    menuMethods.put("2", regionOperation::findById);
    menuMethods.put("3", regionOperation::create);
    menuMethods.put("4", regionOperation::update);
    menuMethods.put("5", regionOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateCityMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all cities");
    menu.put("2", "Select city");
    menu.put("3", "Create city");
    menu.put("4", "Update city");
    menu.put("5", "Delete city");
    return menu;
  }

  private Map<String, Printable> generateCityMenuMethods() {
    Controller<CityEntity> cityController = new CityController();
    ViewOperations<CityEntity> cityOperation =
        new ViewOperations<>(cityController, CityEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", cityOperation::findAll);
    menuMethods.put("2", cityOperation::findById);
    menuMethods.put("3", cityOperation::create);
    menuMethods.put("4", cityOperation::update);
    menuMethods.put("5", cityOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateAddressMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all addresses");
    menu.put("2", "Select address");
    menu.put("3", "Create address");
    menu.put("4", "Update address");
    menu.put("5", "Delete address");
    return menu;
  }

  private Map<String, Printable> generateAddressMenuMethods() {
    Controller<AddressEntity> addressController = new AddressController();
    ViewOperations<AddressEntity> addressOperation =
        new ViewOperations<>(addressController, AddressEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", addressOperation::findAll);
    menuMethods.put("2", addressOperation::findById);
    menuMethods.put("3", addressOperation::create);
    menuMethods.put("4", addressOperation::update);
    menuMethods.put("5", addressOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateManufacturerMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all manufacturers");
    menu.put("2", "Select manufacturer");
    menu.put("3", "Create manufacturer");
    menu.put("4", "Update manufacturer");
    menu.put("5", "Delete manufacturer");
    return menu;
  }

  private Map<String, Printable> generateManufacturerMenuMethods() {
    Controller<ManufacturerEntity> manufacturerController = new ManufacturerController();
    ViewOperations<ManufacturerEntity> manufacturerOperation =
        new ViewOperations<>(manufacturerController,
            ManufacturerEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", manufacturerOperation::findAll);
    menuMethods.put("2", manufacturerOperation::findById);
    menuMethods.put("3", manufacturerOperation::create);
    menuMethods.put("4", manufacturerOperation::update);
    menuMethods.put("5", manufacturerOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateTerminalTypeMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all terminal types");
    menu.put("2", "Select terminal type");
    menu.put("3", "Create terminal type");
    menu.put("4", "Update termnal type");
    menu.put("5", "Delete termina type");
    return menu;
  }

  private Map<String, Printable> generateTerminalTypeMenuMethods() {
    Controller<TerminalTypeEntity> terminalTypeController = new TerminalTypeController();
    ViewOperations<TerminalTypeEntity> terminalTypeOperation =
        new ViewOperations<>(terminalTypeController,
            TerminalTypeEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", terminalTypeOperation::findAll);
    menuMethods.put("2", terminalTypeOperation::findById);
    menuMethods.put("3", terminalTypeOperation::create);
    menuMethods.put("4", terminalTypeOperation::update);
    menuMethods.put("5", terminalTypeOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateTerminalMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all terminals");
    menu.put("2", "Select terminal");
    menu.put("3", "Create terminal");
    menu.put("4", "Update termnal");
    menu.put("5", "Delete termina");
    return menu;
  }

  private Map<String, Printable> generateTerminalMenuMethods() {
    Controller<TerminalEntity> terminalController = new TerminalController();
    ViewOperations<TerminalEntity> terminalOperation = new ViewOperations<>(terminalController,
        TerminalEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", terminalOperation::findAll);
    menuMethods.put("2", terminalOperation::findById);
    menuMethods.put("3", terminalOperation::create);
    menuMethods.put("4", terminalOperation::update);
    menuMethods.put("5", terminalOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateSexMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all sexes");
    menu.put("2", "Select sex");
    menu.put("3", "Create sex");
    menu.put("4", "Update sex");
    menu.put("5", "Delete sex");
    return menu;
  }

  private Map<String, Printable> generateSexMenuMethods() {
    Controller<SexEntity> sexController = new SexController();
    ViewOperations<SexEntity> sexOperation = new ViewOperations<>(sexController, SexEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", sexOperation::findAll);
    menuMethods.put("2", sexOperation::findById);
    menuMethods.put("3", sexOperation::create);
    menuMethods.put("4", sexOperation::update);
    menuMethods.put("5", sexOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generatePositionMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all positions");
    menu.put("2", "Select position");
    menu.put("3", "Create position");
    menu.put("4", "Update position");
    menu.put("5", "Delete position");
    return menu;
  }

  private Map<String, Printable> generatePositionMenuMethods() {
    Controller<PostEntity> positionController = new PostController();
    ViewOperations<PostEntity> positionOperation =
        new ViewOperations<>(positionController, PostEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", positionOperation::findAll);
    menuMethods.put("2", positionOperation::findById);
    menuMethods.put("3", positionOperation::create);
    menuMethods.put("4", positionOperation::update);
    menuMethods.put("5", positionOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateWorkmanMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all workmen");
    menu.put("2", "Select workman");
    menu.put("3", "Create workman");
    menu.put("4", "Update workman");
    menu.put("5", "Delete workman");
    return menu;
  }

  private Map<String, Printable> generateWorkmanMenuMethods() {
    Controller<WorkmanEntity> workmanController = new WorkmanController();
    ViewOperations<WorkmanEntity> workmanOperation =
        new ViewOperations<>(workmanController, WorkmanEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", workmanOperation::findAll);
    menuMethods.put("2", workmanOperation::findById);
    menuMethods.put("3", workmanOperation::create);
    menuMethods.put("4", workmanOperation::update);
    menuMethods.put("5", workmanOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateServiceTypeMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all service types");
    menu.put("2", "Select service type");
    menu.put("3", "Create service type");
    menu.put("4", "Update service type");
    menu.put("5", "Delete service type");
    return menu;
  }

  private Map<String, Printable> generateServiceTypeMenuMethods() {
    Controller<ServiceTypeEntity> serviceTypeController = new ServiceTypeController();
    ViewOperations<ServiceTypeEntity> serviceTypeOperation =
        new ViewOperations<>(serviceTypeController,
            ServiceTypeEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", serviceTypeOperation::findAll);
    menuMethods.put("2", serviceTypeOperation::findById);
    menuMethods.put("3", serviceTypeOperation::create);
    menuMethods.put("4", serviceTypeOperation::update);
    menuMethods.put("5", serviceTypeOperation::delete);
    return menuMethods;
  }

  private Map<String, String> generateServiceMenu() {
    Map<String, String> menu = new LinkedHashMap<>();
    menu.put("1", "Select all services");
    menu.put("2", "Select service");
    menu.put("3", "Create service");
    menu.put("4", "Update service");
    menu.put("5", "Delete service");
    return menu;
  }

  private Map<String, Printable> generateServiceMenuMethods() {
    Controller<ServiceEntity> serviceController = new ServiceController();
    ViewOperations<ServiceEntity> serviceOperation =
        new ViewOperations<>(serviceController, ServiceEntity.class);

    Map<String, Printable> menuMethods = new LinkedHashMap<>();
    menuMethods.put("1", serviceOperation::findAll);
    menuMethods.put("2", serviceOperation::findById);
    menuMethods.put("3", serviceOperation::create);
    menuMethods.put("4", serviceOperation::update);
    menuMethods.put("5", serviceOperation::delete);
    return menuMethods;
  }

  private void showMenuFromMaps(Map<String, String> keyName, Map<String, Printable> keyMethod) {
    String keyMenu;
    do {
      printMenu(keyName);
      System.out.println(TEXT_SELECT_MENU_OPTION);
      keyMenu = input.nextLine().toUpperCase();
      Printable method = keyMethod.get(keyMenu);
      if (method != null) {
        method.print();
      } else if (!keyMenu.equals(KEY_EXIT)) {
        System.out.println(ERROR_NO_SUCH_OPTION);
      }
    } while (!keyMenu.equals(KEY_EXIT));
  }

  private void printMenu(Map<String, String> keyName) {
    for (Entry<String, String> entry : keyName.entrySet()) {
      System.out.format("%3s - %s%n", entry.getKey(), entry.getValue());
    }
    System.out.format("%3s - %s%n", KEY_EXIT, TEXT_GO_BACK);
  }

}
